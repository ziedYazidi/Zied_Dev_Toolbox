package com.dawden;

import java.util.List;

public class Java5LTS {

    public static void main(String[] args) {
        CustomArrayList<String> customArrayList = new CustomArrayList();
        customArrayList.add("1");
        customArrayList.add("2");
        customArrayList.add("3");
//        Allows compile time type safety
//        customArrayList.add(1);

        customArrayList.print();

        System.out.println(customArrayList.get(0));

    }

    //      Generic METHOD
    public <T> void print(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }

    public void printList(List<?> list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }

    public void print(List<? extends Number> numbers) {
        numbers.forEach(element -> System.out.println(element));
    }

    public void add(List<? super Integer> numbers) {
        numbers.add(10);
        Object res = numbers.get(0);
    }


    //    Bounded type parameters
    public <T extends Double> double add(T a, T b) {
        return Double.sum(a, b);
    }

    //    Generic CLASS
    static class CustomArrayList<E> {
        private int size;
        private int nbrInsertions;
        public Object[] content;

        public CustomArrayList() {
            this.size = 5;
            this.nbrInsertions = 0;
            this.content = new Object[this.size];
        }

        public CustomArrayList(int size) {
            this.size = size;
            this.nbrInsertions = 0;
            this.content = new Object[this.size];
        }

        public boolean add(E element) {
            this.content[nbrInsertions] = element;
            this.nbrInsertions++;
            return true;
        }

        public E get(int index) {
            return (E) this.content[index];
        }

        public void print() {
            System.out.print("|");
            for (Object element : this.content) {
                System.out.print(element + "|");
            }
            System.out.println();
        }
    }
}
