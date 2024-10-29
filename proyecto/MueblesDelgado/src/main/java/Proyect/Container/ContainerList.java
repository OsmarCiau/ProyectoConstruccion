package Proyect.Container;

import java.util.ArrayList;
import java.util.Iterator;

public class ContainerList <T extends Container> implements Iterable<T>{
    private ArrayList<T> list = new ArrayList<>(); //puede ser de cualquier objeto que herede de Container;

    public void add(T container){
        list.add(container);
    }

    public void remove(T container){
        list.remove(container);
    }

    public T getByNumber(int number){
        for(T element: list){
            if(number == element.getNumber()){
                return element;
            }
        }
        return null;
    }

    public int size(){
        return list.size();
    }

    @Override
    public Iterator<T> iterator(){
        return list.iterator();
    }

}