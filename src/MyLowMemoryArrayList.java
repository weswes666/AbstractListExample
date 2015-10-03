import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by wessel on 03/10/2015.
 */
public class MyLowMemoryArrayList<E> extends AbstractList {
    private static final short MAX_SIZE = Short.MAX_VALUE;
    private int mSize;
    private Object[] mArray;
    private static final short BASE_SIZE = 10;
    private static final short MAX_SIZE_INCREMENT = 500;

    public MyLowMemoryArrayList() {
        super();
        mSize = 0;
        mArray = new Object[BASE_SIZE];
    }

    public MyLowMemoryArrayList(Collection<E> collection) {
        super();
        mArray = collection.toArray();
        mSize = mArray.length;
    }

    @Override
    public E get(int index) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index > mSize - 1)
            throw new ArrayIndexOutOfBoundsException();
        return (E) mArray[index];
    }

    @Override
    public int size() {
        return mSize;
    }

    @Override
    public E set(int index, Object item) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index > mSize - 1)
            throw new ArrayIndexOutOfBoundsException();
        Object removedObject =  mArray[index];
        mArray[index] = item;
        return (E)removedObject;
    }

    @Override
    public void add(int index, Object item) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index > MAX_SIZE)
            throw new ArrayIndexOutOfBoundsException();
        if(index >= mArray.length) {
            increaseArraySize();
            add(index, item);
        }else {
            mArray[index] = item;
            mSize++;
        }
    }

    @Override
    public E remove(int index) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index > mSize - 1)
            throw new ArrayIndexOutOfBoundsException();

        Object removedObject =  mArray[index];

        mSize--;
        return (E) removedObject;
    }

    @Override
    public String toString(){
        StringBuilder buffer = new StringBuilder();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()){
            buffer.append(iterator.next().toString());
            if(iterator.hasNext())
                buffer.append(", ");
        }
        return buffer.toString();
    }

    private void increaseArraySize() {
        int newSize = Math.min((mArray.length * 2), MAX_SIZE_INCREMENT);
        mArray = Arrays.copyOf(mArray, newSize);
    }
}
