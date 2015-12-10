import java.util.Arrays;
import java.util.NoSuchElementException;

/* code is inspired by:
*  https://www.dropbox.com/s/mpbp6kv0qgg43xp/Heap.java
*  written by Sesh Venugopal
*  but heavily modified to fit criteria
*  
*  Josh Newlin
*/

public class MinHeap {
	private Song[] items;
	private int pos = 0;

	// create new song array
	public MinHeap() {
		items = new Song[500];
	}

	// sift up from bottom
	private int siftUp() {
		boolean flag = false;
		int k = pos;
		while (k > 0) {
			int p = (k - 1) / 2;
			Song item = items[k];
			Song parent = items[p];
			if (item.getP() < parent.getP()) {
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

	// sift up from index
	private void siftUp(int index) {
		boolean flag = false;
		int k = index;
		while (k > 0) {
			int p = (k - 1) / 2;
			Song item = items[k];
			Song parent = items[p];
			if (item.getP() < parent.getP()) {
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

	// insert song
	public int insert(Song item) {
		items[pos] = item;
		int i = siftUp();
		pos++;
		return i;

	}

	// sift down from top
	private void siftDown() {
		boolean flag = false;
		int k = 0;
		int l = 2 * k + 1;
		while (l < pos) {
			int max = l, r = l + 1;
			if (r < pos) { // there is a right child
				if (items[r].getP() < items[l].getP()) {
					max++;
				}
			}
			if (items[k].getP() > items[max].getP()) {
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

	// sift down from index
	private void siftDown(int index) {
		boolean flag = false;
		int k = index;
		int l = 2 * k + 1;
		while (l < pos) {
			int max = l, r = l + 1;
			if (r < pos) { // there is a right child
				if (items[r].getP() < items[l].getP()) {
					max++;
				}
			}
			if (items[k].getP() > items[max].getP()) {
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

	// delete from top
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

	// delete from index
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
			if (hold.getP() < parent.getP()) {
				siftUp(index);
			} else {
				siftDown(index);
			}
		}
		return hold;
	}

	// update hashtable
	public void swapPos(Song num1, Song num2, int thisK, int position) {
		SongCollection.minHash.remove(num1);
		SongCollection.minHash.remove(num2);
		SongCollection.minHash.put(num1, thisK);
		SongCollection.minHash.put(num2, position);
	}

	// update hashtable
	public void swapPos(Song num1, int thisK) {
		SongCollection.minHash.remove(num1);
		SongCollection.minHash.put(num1, thisK);
	}

	// return position of insertion point
	public int size() {
		return pos;
	}

	// return array as a string
	public String toString() {
		return Arrays.toString(items);
	}

	// return top in string
	public String min() {
		return "min " + items[0].getP() + ", ";
	}

	// list all of the array
	public String list() {
		String s = "";
		for (int i = pos - 1; i >= 0; i--) {
			s += i + 1 + ": S" + items[i].getName() + ", pop=" + items[i].getP();
			if (i != 0) {
				s += "\n";
			}
		}
		return s;
	}
}
