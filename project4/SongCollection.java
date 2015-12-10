import java.util.Hashtable;
import java.util.List;

public class SongCollection {
	public MaxHeap max;
	public MinHeap min;
	public static Hashtable<Song, Integer> maxHash;
	public static Hashtable<Song, Integer> minHash;
	public static Hashtable<Integer, Song> hashName;

	public SongCollection() {
		max = new MaxHeap();
		min = new MinHeap();
		maxHash = new Hashtable<Song, Integer>();
		minHash = new Hashtable<Song, Integer>();
		hashName = new Hashtable<Integer, Song>();
	}

	// add a song at date, with the name S{date}
	// and default L and N
	public void addSong(int date) {
		if (max.size() < 500) {
			Song song = new Song(date, 20, 20);
			maxHash.put(song, new Integer(max.insert(song)));
			minHash.put(song, new Integer(min.insert(song)));
			hashName.put(new Integer(song.getName()), song);
			System.out.println("S" + song.getName() + ": N=" + song.getN() + ", L=" + song.getL() + ", pop=" + song.getP());
		} else {
			System.out.println("Error max size exceeded date " + date);
			System.exit(1);
		}

	}

	// delete the n songs of lowest priorities
	public void deleteSong(int n) {
		try {
			if (Project4.i >= 5) {
				check(n);
			}
			for (int i = 0; i < n; i++) {
				Song song = min.delete();
				max.delete(maxHash.get(song));
				minHash.remove(song);
				maxHash.remove(song);
				hashName.remove(song.getName());
				System.out.println("S" + song.getName() + " deleted, pop=" + song.getP());
			}
		} catch (NullPointerException e) {
			System.out.println("Error");
			System.exit(1);
		}
	}

	// update song named by songName
	public void updateSong(int songName, int deltaN, int deltaL) {
		if (hashName.containsKey(songName)) {
			Song song = hashName.get(songName);
			min.delete(minHash.get(song));
			max.delete(maxHash.get(song));
			minHash.remove(song);
			maxHash.remove(song);
			song.changeN(deltaN);
			song.changeL(deltaL);
			minHash.put(song, new Integer(min.insert(song)));
			maxHash.put(song, new Integer(max.insert(song)));
			System.out.println("S" + song.getName() + ": N=" + song.getN() + ", L=" + song.getL() + ", pop=" + song.getP());
		} else {
			Project4.semanticsError(Project4.d);
		}

	}

	// return the 3 most popular songs in the return value
	// with the most popular at index 0
	// the second popular at index 1
	// the third popular at index 2
	public String popular() {
		return max.topThree();
	}

	// return the popularity
	// of the most and least popular songs
	// the 0th integer in the returned object
	// should be the popularity of the most popular song
	// and the 1st integer should be the popularity of
	// the least popular song
	public String minMax() {
		return min.min() + max.max();
	}

	// check if violating size
	private void check(int n) {

		if (max.size() - n < 3) {
			System.out.println("Error min size violated");
			System.exit(1);
		}
	}
}