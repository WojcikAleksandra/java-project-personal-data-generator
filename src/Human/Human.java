package Human;

import Generators.*;
import Generators.Validator.PeselUniqueValidator;
import Generators.Validator.PhoneNumberUniqueValidator;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public class Human {
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String pesel;
    private String city;
    private String address;

    private Human father;
    private Human mother;
    private List<Human> children = new ArrayList<>();

    private List<Human> brothers = new ArrayList<>();

    private List<Human> sisters = new ArrayList<>();

    private Human spouse;

    private String phoneNumber;

    private boolean noRelations=true;



    public Human() {
        this.gender = GenderGenerator.generateGender();

        String[] nameAndSurname;
        if (this.gender.equals("M")) {
            nameAndSurname = NameAndSurnameGenerator.nameAndSurnameForMen();
        } else {
            nameAndSurname = NameAndSurnameGenerator.nameAndSurnameForWomen();
        }
        this.firstName = nameAndSurname[0];
        this.lastName = nameAndSurname[1];

        this.birthDate = BirthDateGenerator.generateBirthDate();
        this.pesel = PeselUniqueValidator.getInstance().validate(
        		birthDate, gender, (birthDate, gender) -> PeselGenerator.generatePesel(birthDate, gender));
        String[] cityAndStreet = AddressesGenerator.addressGenerator();
        this.city = cityAndStreet[0];
        this.address = cityAndStreet[1];
        this.phoneNumber = PhoneNumberUniqueValidator.getInstance().validate(
        		() -> PhoneNumberGenerator.generatePhoneNumber());
    }

    @Override
    public String toString() {
        return "Human{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", pesel='" + pesel + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    // gettery,settery,addery

    public String getAge() {
        LocalDate currentDate = LocalDate.now(); // Pobiera aktualną datę
        return String.valueOf(Period.between(birthDate, currentDate).getYears()); // Oblicza różnicę w latach
    }

    public int getAgeInteger()
    {
        return Integer.parseInt(getAge());

    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNoRelations(boolean noRelations) {
        this.noRelations = noRelations;
    }

    public boolean isNoRelations() {
        return noRelations;
    } //tu jest warning, ale uwazam ze kompilator sie czepia i niech da mi spokoj

    public String getGender() {
        return gender;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public void setFather(Human father)
    {
        this.father=father;
    }

    public Human getFather() {
        return father;
    }

    public Human getMother() {
        return mother;
    }

    public List<Human> getChildren() {
        return children;
    }

    public void addChildren(Human child)
    {
        children.add(child);
    }
    public void delChildren(Human child)
    {
        children.remove(child);

    }

    public void setSpouse(Human spouse) {
        this.spouse = spouse;
    }

    public Human getSpouse() {
        return spouse;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void addBrother(Human brother) {
        brothers.add(brother);
    }

    public List<Human> getBrothers() {
        return brothers;
    }

    public List<Human> getSisters() {
        return sisters;
    }

    public void addSister(Human sister) {
        sisters.add(sister);
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPesel() {
        return pesel;
    }

}
