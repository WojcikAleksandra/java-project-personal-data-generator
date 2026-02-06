package Generators;

import Human.Human;
import PeopleCollectioner.ClassWithCollection;

import java.util.List;

public class GenerateEverything extends Generator
{
    public static List<Human> generateEverything(int n)
    {
        ClassWithCollection classWithCollection = new ClassWithCollection(n);
        RelationshipsGenerator relationshipsGenerator = new RelationshipsGenerator(classWithCollection);
        relationshipsGenerator.generateMotherFatherChildRelations();
        relationshipsGenerator.EstablishMarriages();
        relationshipsGenerator.correctSurnames();
        relationshipsGenerator.correctAddressAndCity();
        relationshipsGenerator.setSiblings();
        relationshipsGenerator.correctPhoneNumber();
        return classWithCollection.getPeopleCollection();
    }




}
