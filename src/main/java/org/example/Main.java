
package org.example;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

class Item {

    int value;
    int weight;

    @Override
    public String toString() {
        return "Item{wartosc=" + value + ", waga=" + weight + "}";
    }
}

class Result {

    int amount;
    int tot_weight;
    int tot_val;
    LinkedList<Item> idlista = new LinkedList<Item>(); //lista id

}

class Problem {

    int kinds = 2;
    int seed;
    int min= 1;
    int max = 10;
    LinkedList<Item> lista = new LinkedList<Item>();

    public Problem(){

        Scanner myObj = new Scanner(System.in);
        System.out.println("Podaj liczbe rodzajow przedmiotow: ");
        int kinds = myObj.nextInt();
        System.out.println("Podaj ziarno: ");
        int seed = myObj.nextInt();

        Random generator = new Random(seed);
        for (int i = 1; i <= kinds ; i++)
        {
            Item item = new Item();
            item.value = generator.nextInt(min,max);
            item.weight = generator.nextInt(min,max);
            int ammount = generator.nextInt(min,max);
            for (int j = 1; j <= ammount; j++)
            {
                lista.add(item);
            }
        }


    }

    public Result solve(int capacity)
    {
        this.lista.sort((i1, i2) -> {
            double ratio1 = (double) i1.value / i1.weight;
            double ratio2 = (double) i2.value / i2.weight;
            return Double.compare(ratio2, ratio1);
        });
        Result result = new Result();
        int currentWeight = 0;

        for (Item item : this.lista) {
            if (currentWeight + item.weight <= capacity) {
                result.idlista.add(item);
                result.tot_val += item.value;
                result.tot_weight += item.weight;
                currentWeight += item.weight;
                result.amount++;
            } else {
                continue;
            }
        }

        return result;
    }


}

public class Main {
    static void main() {
        Problem problem = new Problem();
        System.out.println("\nWygenerowanie problemu: ");
        for (Item item : problem.lista) {
            System.out.println(item);
        }

        int plecakCapacity = 45;
        Result wynik = problem.solve(plecakCapacity);

        //Rozwiązanie problemu
        System.out.println("\nRozwiazanie problemu: ");
        for (Item item : wynik.idlista) {
            System.out.println(item);
        }
        System.out.println("\nLiczba przedmiotow w plecaku: " + wynik.amount);
        System.out.println("Laczna waga: " + wynik.tot_weight);
        System.out.println("Laczna wartosc: " + wynik.tot_val);
    }
}
