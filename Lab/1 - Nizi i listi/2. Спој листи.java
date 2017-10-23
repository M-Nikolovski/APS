import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class SLLJoinLists {
    public static void main(String[] args) throws IOException {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        SLL<Integer> lista1 = new SLL<Integer>();
        SLLNode<Integer> temp = new SLLNode<>(Integer.parseInt(pomniza[0]), null);
        for (int i = 0; i < N; i++) {
            lista1.insertLast(Integer.parseInt(pomniza[i]));
        }

        s = stdin.readLine();
        N = Integer.parseInt(s);
        s = stdin.readLine();
        pomniza = s.split(" ");
        SLL<Integer> lista2 = new SLL<Integer>();
        for (int i = 0; i < N; i++) {
            lista2.insertLast(Integer.parseInt(pomniza[i]));
        }

        SLL<Integer> spoeni = new SLL<Integer>();
        spoeni = lista1.joinLists(lista2);
        Iterator<Integer> it = spoeni.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
            if(it.hasNext())
                System.out.print(" ");
        }
        System.out.println();
    }
}



class SLLNode<E> {

    private E element;
    private SLLNode<E> succ;

    public SLLNode(E element, SLLNode<E> succ) {
        this.element = element;
        this.succ = succ;
    }

    public E getElement() { return element; }
    public SLLNode<E> getSucc() { return succ; }

    public void setElement(E element) { this.element = element; }
    public void setSucc(SLLNode<E> succ) { this.succ = succ; }

}

class SLL<E> {

    private SLLNode<E> first;

    public SLL() {
        this.first = null;
    }

    public void insertFirst(E element) {
        first = new SLLNode<E>(element, first);
    }
    public void insertLast(E element) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.getSucc() != null) {
                tmp = tmp.getSucc();
            }
            tmp.setSucc(new SLLNode<E>(element, null));
        }
        else {
            insertFirst(element);
        }
    }

    public SLL<E> joinLists(SLL<E> list) {
        SLLNode<E> thisTmp = first;
        SLLNode<E> listTmp = list.first;
        SLL<E> result = new SLL<E>();
        while ((thisTmp != null) && (listTmp != null)) {
            if ((int)thisTmp.getElement() < (int)listTmp.getElement()) {
                result.insertLast(thisTmp.getElement());
                thisTmp = thisTmp.getSucc();
            }
            else if ((int)thisTmp.getElement() > (int)listTmp.getElement()) {
                result.insertLast(listTmp.getElement());
                listTmp = listTmp.getSucc();
            }
            else {
                result.insertLast(thisTmp.getElement());
                thisTmp = thisTmp.getSucc();
                listTmp = listTmp.getSucc();
            }
        }
        while (thisTmp != null) {
            result.insertLast(thisTmp.getElement());
            thisTmp = thisTmp.getSucc();
        }
        while (listTmp != null) {
            result.insertLast(listTmp.getElement());
            listTmp = listTmp.getSucc();
        }
        return result;
    }

    public Iterator<E> iterator () {
        return new LRIterator<E>();
    }
    private class LRIterator<E> implements Iterator<E> {
        private SLLNode<E> place, prev, curr;
        private LRIterator() {
            place = (SLLNode<E>) first;
            curr = prev = null;
        }
        public boolean hasNext() {
            return (place != null);
        }
        public E next() {
            if (place == null)
                throw new NoSuchElementException();
            E nextElem = place.getElement();
            prev = curr;
            curr = place;
            place = place.getSucc();
            return nextElem;
        }
    }
}
