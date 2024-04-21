package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private LocalDate joinDate; // Menggunakan LocalDate untuk representasi tanggal bergabung
    private Gender gender; // Menggunakan enum untuk representasi jenis kelamin

    private boolean isForeigner;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate joinDate, Gender gender, boolean isForeigner) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.joinDate = joinDate;
        this.gender = gender;
        this.isForeigner = isForeigner;

        childNames = new LinkedList<>();
        childIdNumbers = new LinkedList<>();
    }

    public void setMonthlySalary(int grade) {
        int baseSalary;
        switch (grade) {
            case 1:
                baseSalary = 3000000;
                break;
            case 2:
                baseSalary = 5000000;
                break;
            case 3:
                baseSalary = 7000000;
                break;
            default:
                baseSalary = 0; 
        }
        monthlySalary = isForeigner ? (int) (baseSalary * 1.5) : baseSalary;
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseidNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {
        LocalDate currentDate = LocalDate.now();
        int monthsWorked = 12; // Jika bergabung pada tahun sebelumnya, dianggap bekerja selama 12 bulan
        if (currentDate.getYear() == joinDate.getYear()) {
            monthsWorked = currentDate.getMonthValue() - joinDate.getMonthValue();
        }
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorked, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }

    // Enum untuk representasi jenis kelamin
    public enum Gender {
        LAKI_LAKI,
        PEREMPUAN
    }
}
