package Proyect.Container;

import Proyect.Validations.ValidationUtils;

import java.util.ArrayList;
import java.util.Iterator;

public class ContainerList <T extends Container> implements Iterable<T>{
    private ArrayList<T> list = null; //puede ser de cualquier objeto que herede de Container;

    public ContainerList() {
        this.list = new ArrayList<>();
    }

    public void add(T p_container){
        ValidationUtils.validateNonNull(p_container, "Container");
        this.list.add(p_container);
    }

    public void remove(T p_container){
        this.list.remove(p_container);
    }

    public T getByNumber(int p_number){
        for(T element: this.list){
            if(p_number == element.getNumber()){
                return element;
            }
        }
        return null;
    }

    public int size(){
        return this.list.size();
    }

    public boolean isEmpty(){
        return this.list.isEmpty();
    }

    @Override
    public Iterator<T> iterator(){
        return this.list.iterator();
    }

}