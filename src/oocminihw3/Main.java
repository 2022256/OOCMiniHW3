/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oocminihw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author 4istik
 */
    class Person { // create person
    String id;
    String firstName;
    String lastName;
    String email;

    public Person(String id, String firstName, String lastName, String email) { //constructor
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

    class Team { // create team
    String name;
    List<Person> members;

    public Team(String name) { // constructor + array
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(Person person) { // void method to add a person
        members.add(person);
    }
}

    public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        // Read data from the file and populate the list of people
        try (BufferedReader br = new BufferedReader(new FileReader("MOCK_DATA.CSV"))) { 
            String line;
            while ((line = br.readLine()) != null) { // while has data
                String[] data = line.split(",");  //data divided by coma 
                Person person = new Person(data[0], data[1], data[2], data[3]); // stored 4 variables to create a person
                people.add(person); // add person to the array
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // shuffle the list of people randomly, import Collections
        Collections.shuffle(people);

        // create 20 teams with 5 members each
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Team team = new Team("Team " + (i + 1)); // team 1 , 2 , 3 etc
            for (int j = 0; j < 5; j++) { // 5 members each team
                if (!people.isEmpty()) { 
                    Person person = people.remove(0);
                    team.addMember(person);
                }
            }
            teams.add(team);
        }

        // Print each team and its members
        for (Team team : teams) {
            System.out.println("Team Name: " + team.name);
            for (Person member : team.members) {
                System.out.println("  ID: " + member.id + ", Name: " + member.firstName + " " + member.lastName + ", Email: " + member.email);
            }
            System.out.println();
        }
    }
}
