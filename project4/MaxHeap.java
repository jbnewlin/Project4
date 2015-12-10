import java.util.Arrays;
import java.util.NoSuchElementException;

/* code is inspired by:
*  https://www.dropbox.com/s/mpbp6kv0qgg43xp/Heap.java
*  written by Sesh Venugopal
*  but heavily modified to fit criteria
*  
*  Josh Newlin
*/

public class MaxHeap {
	private Song[] items;
	private int pos = 0;

	//create new song array
	public MaxHeap() {
		items = new Song[500];
	}

	//sift up at bottom
	private int siftUp() {
		boolean flag = false;
		int k = pos;
		while (k > 0) {
			int p = (k - 1) / 2;
			Song item = items[k];
			Song parent = items[p];
			if (item.getP() > parent.getP()) {
				// swap
				flag = true;
				items[k] = parent;
				items[p] = item;
				swapPos(items[k], items[p], k, p);
				// move up one level
				k = p;

			} else {
				break;
			}
		}
		if (!flag) {
			swapPos(items[k], k);
		}
		return k;
	}

	//siftup at index
	private void siftUp(int index) {
		boolean flag = false;
		int k = index;
		while (k > 0) {
			int p = (k - 1) / 2;
			Song item = items[k];
			Song parent = items[p];
			if (item.getP() > parent.getP()) {
				// swap
				flag = true;
				items[k] = parent;
				items[p] = item;
				swapPos(items[k], items[p], k, p);
				// move up one level
				k = p;

			} else {
				break;
			}
		}
		if (!flag) {
			swapPos(items[k], k);
		}
	}

	//insert song
	public int insert(Song item) {
		items[pos] = item;
		int i = siftUp();
		pos++;
		return i;

	}

	//sift down from top
	private void siftDown() {
		boolean flag = false;
		int k = 0;
		int l = 2 * k + 1;
		while (l < pos) {
			int max = l, r = l + 1;
			if (r < pos) { // there is a right child
				if (items[r].getP() > items[l].getP()) {
					max++;
				}
			}
			if (items[k].getP() < items[max].getP()) {
				// switch
				flag = true;
				Song temp = items[k];
				items[k] = items[max];
				items[max] = temp;
				swapPos(items[k], items[max], k, max);
				k = max;
				l = 2 * k + 1;
			} else {
				break;
			}
		}
		if (!flag) {
			swapPos(items[k], k);
		}
	}

	//siftdown at index
	private void siftDown(int index) {
		boolean flag = false;
		int k = index;
		int l = 2 * k + 1;
		while (l < pos) {
			int max = l, r = l + 1;
			if (r < pos) { // there is a right child
				if (items[r].getP() > items[l].getP()) {
					max++;
				}
			}
			if (items[k].getP() < items[max].getP()) {
				// switch
				flag = true;
				Song temp = items[k];
				items[k] = items[max];
				items[max] = temp;
				swapPos(items[k], items[max], k, max);
				k = max;
				l = 2 * k + 1;
			} else {
				break;
			}
		}
		if (!flag) {
			swapPos(items[k], k);
		}
	}

	//delete top
	public Song delete() throws NoSuchElementException {
		if (pos == 0) {
			throw new NoSuchElementException();
		}
		if (pos == 1) {
			items[0] = null;
			--pos;
			return null;
		}
		Song hold = items[0];
		items[0] = items[--pos];
		items[pos] = null;
		siftDown();
		return hold;
	}

	//delete at index
	public Song delete(int index) throws NoSuchElementException {
		Song hold;
		if (pos < index)
			throw new NoSuchElementException();
		if (index < 0) {
			throw new NoSuchElementException();
		}
		if (index == 0) {
			return delete();
		}
		if (index == pos - 1) {
			hold = items[index];
			items[index] = null;
			--pos;
		} else {
			hold = items[index];
			items[index] = items[--pos];
			items[pos] = null;
			Song parent = items[(index - 1) / 2];
			if (hold.getP() > parent.getP()) {
				siftUp(index);
			} else {
				siftDown(index);
			}
		}
		return hold;
	}

	//update hashtable
	public void swapPos(Song num1, Song num2, int thisK, int position) {
		SongCollection.maxHash.remove(num1);
		SongCollection.maxHash.remove(num2);
		SongCollection.maxHash.put(num1, thisK);
		SongCollection.maxHash.put(num2, position);
	}

	//update hashtable
	public void swapPos(Song num1, int thisK) {
		SongCollection.maxHash.remove(num1);
		SongCollection.maxHash.put(num1, thisK);
	}

	//returns position of insertion point
	public int size() {
		return pos;
	}

	//prints out the array
	public String toString() {
		return Arrays.toString(items);
	}

	// returns the top value in string form
	public String max() {
		return "max " + items[0].getP() + "\n";
	}
	
	//returns the top 3 in the max heap
	public String topThree() {
		Song[] songs = new Song[3];
		for (int i = 0; i < 7; i++) {
			if (items[i] != null) {
				if (i >= 0 && i < 3) {
					songs[i] = items[i];
				} else {
					if (i == 3 && songs[1].getP() < songs[2].getP()) {
						Song temp = songs[1];
						songs[1] = songs[2];
						songs[2] = temp;
					}
					if (items[i].getP() == songs[0].getP()) {
						if (items[i].getName() < songs[0].getName()) {
							songs[0] = items[i];
						}
					}
					if (items[i].getP() == songs[1].getP()) {
						if (items[i].getName() < songs[1].getName()) {
							songs[1] = items[i];
						}
					}
					if (items[i].getP() == songs[2].getP()) {
						if (items[i].getName() < songs[2].getName()) {
							songs[2] = items[i];
						}
					}
					if (items[i].getP() > songs[2].getP()) {
						songs[2] = items[i];
					}
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			if (i != 2) {
				if (songs[i].getName() > songs[i + 1].getName()) {
					if (songs[i].getP() == songs[i + 1].getP()) {
						Song temp = songs[i];
						songs[i] = songs[i + 1];
						songs[i + 1] = temp;
						i = 0;
					}
				}
			}
		}
		String s = "";
		for (int i = 0; i < 3; i++) {
			s += i+1 + ": S" + songs[i].getName() + ", pop=" + songs[i].getP();
			if (i != 2) {
				s += "\n";
			}
		}

		return s;
	}
}
