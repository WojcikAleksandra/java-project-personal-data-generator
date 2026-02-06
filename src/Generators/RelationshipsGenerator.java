package Generators;

import Human.Human;
import PeopleCollectioner.ClassWithCollection;
import java.util.*;
import java.util.stream.Collectors;


public class RelationshipsGenerator extends Generator
{
    private final ClassWithCollection classWithCollection;

    public RelationshipsGenerator(ClassWithCollection classWithCollection)
    {

        this.classWithCollection=classWithCollection;
    }


    protected void generateMotherFatherChildRelations()
    {

        List<Human> peopleCollection = classWithCollection.getPeopleCollection();

        Collections.shuffle(peopleCollection);

        int halfOfCollection = peopleCollection.size() / 2;
        for (int i = 0; i < halfOfCollection; i++) {
            peopleCollection.get(i).setNoRelations(false);
        }

        Collections.shuffle(peopleCollection);

        List<List<Human>> dividedGroups = divideList(peopleCollection);

        for (List<Human> group : dividedGroups) {

            for (Human person : group)
            {
                if (!person.isNoRelations())
                {

                    double randomDouble1 = rand.nextDouble();
                    double randomDouble2 = rand.nextDouble();

                    if(randomDouble1<0.5)
                    {
                        String gender;
                        if(randomDouble2<0.5){
                            gender= String.valueOf('M');
                            Human father = findRightParent(gender,group,person);
                            person.setFather(father);

                            if(father != null)
                            {
                                if(!father.getChildren().isEmpty())
                                {
                                    if(father.getChildren().get(0).getMother() != null) {
                                        Human mother = father.getChildren().get(0).getMother();
                                        mother.addChildren(person);
                                        person.setMother(mother);
                                        }
                                }

                                father.addChildren(person);
                            }

                        }
                        else {
                            gender = String.valueOf('F');
                            Human mother = findRightParent(gender, group, person);
                            person.setMother(mother);
                            if(mother != null) {
                                if(!mother.getChildren().isEmpty()) {
                                    if(mother.getChildren().get(0).getFather() != null) {
                                        Human father = mother.getChildren().get(0).getFather();
                                        father.addChildren(person);
                                        person.setFather(father);
                                    }
                                }
                                mother.addChildren(person);
                            }
                        }
                    }

                    else
                    {

                        Human mother = findRightParent("F", group, person);
                        person.setMother(mother);

                        // jesli jakas wylosowana matka


                        if(mother != null)
                        {
                            if(!mother.getChildren().isEmpty())
                            {
                                Human child = mother.getChildren().get(0);
                                if(child.getFather() != null) {
                                    person.setFather(child.getFather());
                                    child.getFather().addChildren(person);
                                }
                                else{ // zapobiegamy niebożym sytuacjom
                                    mother.addChildren(person);
                                }
                                continue;

                            }
                            mother.addChildren(person);
                        }

                        else
                            continue;

                        // tutaj sytuacja ze matka nie ma dzieci innych
                        Human father = findPartnerToCopulateWith(mother,group);
                        if(father!=null){
                            if(!father.getChildren().isEmpty())
                            {
                                for(Human child: father.getChildren())
                                {
                                    if(child.getMother()!=null)
                                        child.getMother().delChildren(child);

                                    child.setMother(mother);
                                    mother.addChildren(child);
                                }

                            }
                            person.setFather(father);
                            father.addChildren(person);}

                    }
                }
            }
        }

    }


     protected void EstablishMarriages() {
         List<Human> peopleCollection = classWithCollection.getPeopleCollection();

         for (Human person : peopleCollection) {
             if (person.getMother() != null & person.getFather() != null) {
                 person.getMother().setSpouse(person.getFather());
                 person.getFather().setSpouse(person.getMother());
             }
         }


         List<Human> peopleWithoutChildren = peopleCollection.stream()
                 .filter(human -> (human.getChildren() == null || human.getChildren().isEmpty()) && human.getAgeInteger() > 23)
                 .collect(Collectors.toList());

         int numberToRemove = (int) (peopleWithoutChildren.size() * 0.30); // Obliczanie 30% listy
         peopleWithoutChildren.subList(0, numberToRemove).clear(); // Usuwanie 30% elementów

         Collections.shuffle(peopleWithoutChildren);

         List<List<Human>> dividedGroups = divideList(peopleWithoutChildren);

         for (List<Human> group : dividedGroups) {

             for (Human person : group)
             {
                    if(person.getSpouse() == null) {
                        Human spouse = findSpouse(person, group);
                        if (spouse != null) {
                            person.setSpouse(spouse);
                            spouse.setSpouse(person);
                        }
                    }
             }
         }
     }


     protected void correctSurnames()
     {
         List<Human> collection = classWithCollection.getPeopleCollection();
         collection.sort((h1, h2) -> Integer.compare(h2.getAgeInteger(), h1.getAgeInteger()));

         for(Human person: collection)
        {
            if(person.getFather()!=null)
                person.setLastName(person.getFather().getLastName());
            else if (person.getMother()!=null)
                person.setLastName(person.getMother().getLastName());
        }

        for(Human person: collection)
        {
            if("F".equals(person.getGender()) & person.getSpouse()!=null)
                person.setLastName(person.getSpouse().getLastName());

        }

         for(Human person: collection)
         {
             if("F".equals(person.getGender()))
             {
                 person.setLastName(NameAndSurnameGenerator.adjustSurnameForWomen(person.getLastName()));
             }
         }
     }

     protected void correctAddressAndCity()
     {
         List<Human> collection = classWithCollection.getPeopleCollection();

         List<Human> peopleUnder20 = collection.stream()
                 .filter(human -> human.getAgeInteger() <= 20).toList();

         for(Human person: peopleUnder20)
         {

             if(person.getFather()!=null) {
                 person.setCity(person.getFather().getCity());
                 person.setAddress(person.getFather().getAddress());
             }

             else if(person.getMother()!=null) {
                 person.setCity(person.getMother().getCity());
                 person.setAddress(person.getMother().getAddress());
             }
         }

         for(Human person: collection)
         {
             if(Objects.equals(person.getGender(), "F") & person.getSpouse()!=null)
             {
                 person.setCity(person.getSpouse().getCity());
                 person.setAddress(person.getSpouse().getAddress());
             }
         }

     }

    protected void setSiblings() {
        List<Human> people = classWithCollection.getPeopleCollection();

        for (Human person : people) {
            // Ustawienie braci
            if (person.getMother() != null) {
                for (Human sibling : person.getMother().getChildren()) {
                    if (!sibling.equals(person) && "M".equals(sibling.getGender()) && !person.getBrothers().contains(sibling)) {
                        person.addBrother(sibling);
                    }
                }
            }
            if (person.getFather() != null) {
                for (Human sibling : person.getFather().getChildren()) {
                    if (!sibling.equals(person) && "M".equals(sibling.getGender()) && !person.getBrothers().contains(sibling)) {
                        person.addBrother(sibling);
                    }
                }
            }

            // Ustawienie sióstr
            if (person.getMother() != null) {
                for (Human sibling : person.getMother().getChildren()) {
                    if (!sibling.equals(person) && "F".equals(sibling.getGender()) && !person.getSisters().contains(sibling)) {
                        person.addSister(sibling);
                    }
                }
            }
            if (person.getFather() != null) {
                for (Human sibling : person.getFather().getChildren()) {
                    if (!sibling.equals(person) && "F".equals(sibling.getGender()) && !person.getSisters().contains(sibling)) {
                        person.addSister(sibling);
                    }
                }
            }
        }
    }






    private Human findRightParent(String gender, List<Human> group,Human child)
    {
        List<Human> copiedGroup = new ArrayList<>(group); // Tworzenie kopii listy
        Collections.shuffle(copiedGroup); // Mieszanie skopiowanej grupy
        for (Human person : copiedGroup)
        {
            if(!person.isNoRelations())
                if(Objects.equals(person.getGender(), gender) & (person.getAgeInteger()-child.getAgeInteger())>18 & (person.getAgeInteger()-child.getAgeInteger())<34)
                    return person;
        }
        return null;
    }

    private Human findPartnerToCopulateWith(Human mother, List<Human> group)
    {
        List<Human> copiedGroup = new ArrayList<>(group); // Tworzenie kopii listy
        Collections.shuffle(copiedGroup); // Mieszanie skopiowanej grupy
        for (Human father : copiedGroup)
        {
            if(!father.isNoRelations())
                if(Objects.equals(father.getGender(), "M") & (mother.getAgeInteger()-father.getAgeInteger())<=5 & (father.getAgeInteger()-mother.getAgeInteger())<=10)
                    return father;
        }
        return null;
    }


    private Human findSpouse(Human person, List<Human> group)
    {
        List<Human> copiedGroup = new ArrayList<>(group); // Tworzenie kopii listy
        Collections.shuffle(copiedGroup);

        String gender = person.getGender();


        for(Human person1 : copiedGroup)
        {
            if(!Objects.equals(person1.getGender(), gender) & person1.getSpouse() == null & Math.abs(person.getAgeInteger()- person1.getAgeInteger())<6 )
                return person1;
        }

        return null;

    }

    protected void correctPhoneNumber()
    {
        List <Human> collection = classWithCollection.getPeopleCollection();
        for (Human human: collection)
        {
            adjustPhoneNumber(human);
        }
    }

    private void adjustPhoneNumber(Human human) {
        int age = Integer.parseInt(human.getAge());
        double probability = getProbabilityBasedOnAge(age);
        boolean hasPhoneNumber = rand.nextDouble() < probability;

        if (!hasPhoneNumber) {
            human.setPhoneNumber("000000000"); // Ustawienie 9 zer, jeśli nie ma numeru telefonu
        }
    }



    private <T> List<List<T>> divideList(List<T> list) {
        int groupSize = 50;
        List<List<T>> groups = new ArrayList<>();
        for (int i = 0; i < list.size(); i += groupSize) {
            groups.add(new ArrayList<>(list.subList(i, Math.min(i + groupSize, list.size()))));
        }
        return groups;
    }


    private double getProbabilityBasedOnAge(int age) {
        if (age >= 0 && age <= 3) return 0.001;
        if (age == 4) return 0.05;
        if (age == 5) return 0.15;
        if (age == 6) return 0.30;
        if (age == 7) return 0.50;
        if (age == 8) return 0.65;
        if (age == 9) return 0.80;
        if (age == 10) return 0.90;
        if (age == 11) return 0.95;
        if (age == 12) return 0.98;
        if (age >= 13 && age <= 17) return 0.99;
        if (age >= 18 && age <= 75) return 0.995;
        if (age > 75 && age <= 80) return 0.98;
        if (age > 80) return 0.95;

        return 0;
    }

}
