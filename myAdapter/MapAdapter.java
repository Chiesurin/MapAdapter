package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import myExceptions.UnsupportedOperationException;

public class MapAdapter implements HMap {

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------- NESTED CLASSES -------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	final protected class Entry implements HMap.HEntry {
		private Object key;
		private Object value;

		//lancia eccezione se qualcosa null
		private Entry(Object key, Object value){
			if(key == null || value == null)
				throw new NullPointerException();
			this.key = key;
			this.value = value;
		}

		//non può mai essere null quindi ho rimosso i controlli originale, commentare
		public boolean equals(Object o) {
			if (!(o instanceof HEntry) || o == null) {
				return false;
			}

			HEntry e = (HEntry)o;
			
			return  this.getKey().equals(e.getKey())  
					&&
					this.getValue().equals(e.getValue());
		}

		public Object getKey(){
			return this.key;
		}

		public Object getValue(){
			return this.value;
		}

		//non può mai essere null quindi ho rimosso i controlli originale, commentare
		public int hashCode(){
			return  this.getKey().hashCode() 
					^ 
					this.getValue().hashCode();
		}

		//lancia nullpointerexceptino se value null
		//da testare bene e provare anche la versione base se funziona
		//nella nostra implementazione il caso speciale rimette la entry dentro la mappa
		//in effetti non è specificato il comportamento in questo caso, quindi ogni scelta va bene
		public Object setValue(Object value){
			Object oldValue = hashTable.put(this.key, value);
			this.value = value;
			return oldValue;
		}
	}

	/*-------------------------------------------------------------------------------------------*/
	/*----------------------------------- ITERATORS HIERARCHY -----------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	protected class EntrySetIterator implements HIterator {
		protected Enumeration enumeration;
		protected Object current;

		protected EntrySetIterator() {
			this.enumeration = hashTable.keys();
			this.current = null;
		}

		public boolean hasNext() {
			return this.enumeration.hasMoreElements();
		}

		public Object next() {
			this.current = this.enumeration.nextElement();
			return new Entry(current, hashTable.get(current));
		}

		public void remove(){
			if(current == null)
				throw new IllegalStateException("next should be called first.");
			
			hashTable.remove(current);
			this.current = null;
		}
	}
	
	final protected class KeySetIterator extends EntrySetIterator {
		@Override
		public Object next() {
			this.current = this.enumeration.nextElement();
			return this.current;
		}
	}

	final protected class ValueCollectionIterator extends EntrySetIterator {
		@Override
		public Object next() {
			this.current = this.enumeration.nextElement();
			return hashTable.get(this.current);
		}
	}

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------------------------------------------------*/
	/*----------------------------------- COLLECTION HIERARCHY ----------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	abstract protected class AbstractCollection implements HCollection {
		public boolean add(Object o){
			throw new UnsupportedOperationException();
		}

		public boolean addAll(HCollection c){
			throw new UnsupportedOperationException();
		}

		public void clear() {
			hashTable.clear();
		}

		public boolean containsAll(HCollection c) {
			if(c == null)
				throw new NullPointerException();

			HIterator iterator = c.iterator();
			while(iterator.hasNext()){
				if(!this.contains(iterator.next()))
					return false;
			}
			return true;
		}

		public boolean equals(Object o) {
			if (!(o instanceof HCollection) || o == null)
				return false;

			if(o == this)
				return true;

			HCollection c = (HCollection)o;
			
			return this.size() == c.size() && this.containsAll(c);
		}

		public int hashCode() {
			int hash = 0;
			HIterator iterator = this.iterator();
			while(iterator.hasNext()){
				hash += iterator.next().hashCode();
			}
			return hash;
		}

		public boolean isEmpty() {
			return hashTable.isEmpty();
		}

		public boolean removeAll(HCollection c) {
			if(c == null)
				throw new NullPointerException();

			boolean modified = false;
			HIterator iterator = c.iterator();
			while(iterator.hasNext()){
				if(this.remove(iterator.next()))
					modified = true;
			}
			return modified;
		}

		public boolean retainAll(HCollection c) {
			if(c == null)
				throw new NullPointerException();

			boolean modified = false;
			HIterator iterator = this.iterator();
			while(iterator.hasNext()){
				Object next = iterator.next();
				if(!c.contains(next)){
					iterator.remove();
					modified = true;
				}
			}
			return modified;
		}

		public int size() {
			return hashTable.size();
		}

		@Override
		public Object[] toArray() {
			Object[] array = new Object[this.size()];
			HIterator iterator = this.iterator();
			int i = 0;
			while(iterator.hasNext()){
				array[i++] = iterator.next();
			}
			return array;
		}

		//essendo in CLDC1.1 non supporta reflection, non è possibile adempiere alle richieste originali, indagare
		@Override
		public Object[] toArray(Object[] a) {
			if(a == null)
				throw new NullPointerException();
			
			if(a.length < this.size())
				a = new Object[this.size()];

			HIterator iterator = this.iterator();
			int i = 0;
			while(iterator.hasNext()){
				a[i++] = iterator.next();
			}

			if(i < a.length)
				a[i] = null;

			return a;
		}
	}

	final protected class EntrySet extends AbstractCollection implements HSet {
		public boolean contains(Object o) {
			if(o == null)
				throw new NullPointerException();
			
			if(!(o instanceof HEntry))
				throw new ClassCastException();
			
			return  hashTable.containsKey(((HEntry)o).getKey()) 
					&& 
					hashTable.get(((HEntry)o).getKey()).equals(((HEntry)o).getValue());
		}

		public boolean remove(Object o) {
			if(this.contains(o))
				return hashTable.remove(((HEntry)o).getKey()) != null;
			return false;
		}

		public HIterator iterator() {
			return new EntrySetIterator();
		}
	}

	final protected class KeySet extends AbstractCollection implements HSet {
		public boolean contains(Object o) {
			if(o == null)
				throw new NullPointerException();
			
			return hashTable.containsKey(o);
		}

		public boolean remove(Object o) {
			if(o == null)
				throw new NullPointerException();
			
			return hashTable.remove(o) != null;
		}

		public HIterator iterator() {
			return new KeySetIterator();
		}
	}

	final protected class ValueCollection extends AbstractCollection {
		public boolean contains(Object o) {
			if(o == null)
				throw new NullPointerException();
			
			return hashTable.contains(o);
		}

		public boolean remove(Object o) {
			if(o == null)
				throw new NullPointerException();
			
			HSet entrySet = entrySet();
			HIterator iterator = entrySet.iterator();
			while(iterator.hasNext()){
				HEntry entry = (HEntry) iterator.next();
				if(entry.getValue().equals(o)){
					hashTable.remove(entry.getKey());
					return true;
				}
			}

			return false;
		}

		public HIterator iterator() {
			return new ValueCollectionIterator();
		}
	}

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------------------------------------------------*/
	/*--------------------------------------- PARAMETERS ----------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	private Hashtable hashTable;

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------------------------------------------------*/
	/*--------------------------------------  CONSTRUCTORS --------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	public MapAdapter(){
		this.hashTable = new Hashtable();
	}
	
	public MapAdapter(HMap t){
		this.hashTable = new Hashtable();
		this.putAll(t);
	}

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/

	/*-------------------------------------------------------------------------------------------*/
	/*------------------------------------ INTERFACE METHODS ------------------------------------*/ 
	/*-------------------------------------------------------------------------------------------*/

	public void clear() {
		this.hashTable.clear();
	}

	//lancio nullpointerexception per mantenere
	//uniformità con containsValue
	public boolean containsKey(Object key) {
		if(key == null)
			throw new NullPointerException();
		
		return this.hashTable.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return this.hashTable.contains(value);
	}

	public HSet entrySet() {
		return new EntrySet();
	}

	public boolean equals(Object o) {
		if (!(o instanceof HMap) ||  o == null)
			return false;

		if(o == this)
			return true;

		HMap m = (HMap)o;
		
		return this.entrySet().equals(m.entrySet());
	}

	//lancia eccezione sempre per mantenere uniformità, get non lancerebbe
	public Object get(Object key) {
		if(key == null)
			throw new NullPointerException();

		return this.hashTable.get(key);
	}

	public int hashCode() {
		return this.entrySet().hashCode();
	}

	public boolean isEmpty() {
		return this.hashTable.isEmpty();
	}

	public HSet keySet() {
		return new KeySet();
	}

	//commentare bene quell'illegalargumentexception
	public Object put(Object key, Object value) {
		if(key == this)
			throw new IllegalArgumentException();

		return this.hashTable.put(key, value);
	}

	//chiamo entrySet(), faccio put delle entry iterandole
	public void putAll(HMap t) {
		if(t == null)
			throw new NullPointerException();

		HSet set = t.entrySet();
		HIterator iterator = set.iterator();
		while(iterator.hasNext()){
			HEntry entry = (HEntry) iterator.next();
			this.put(entry.getKey(), entry.getValue());
		}
	}

	//lancia eccezione per uniformità, remove non lancerebbe eccezione
	public Object remove(Object key) {
		if(key == null)
			throw new NullPointerException();
		return this.hashTable.remove(key);
	}

	public int size() {
		return this.hashTable.size();
	}

	public HCollection values() {
		return new ValueCollection();
	}

	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/
}