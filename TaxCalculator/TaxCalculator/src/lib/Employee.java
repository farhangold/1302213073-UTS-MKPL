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
		if (grade >= 1 && grade <= 3) {
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
		} else {
			throw new IllegalArgumentException("Grade harus berada dalam rentang 1 hingga 3.");
		}
	}

	public void setAnnualDeductible(int deductible) {
		if (deductible >= 0) {
			this.annualDeductible = deductible;
		} else {
			throw new IllegalArgumentException("Deductible tidak boleh negatif.");
		}
	}

	public void setAdditionalIncome(int income) {
		if (income >= 0) {
			this.otherMonthlyIncome = income;
		} else {
			throw new IllegalArgumentException("Additional income tidak boleh negatif.");
		}
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		if (spouseIdNumber != null && !spouseIdNumber.isEmpty()) {
			this.spouseName = spouseName;
			this.spouseIdNumber = spouseIdNumber;
		} else {
			throw new IllegalArgumentException("Spouse ID Number tidak boleh kosong.");
		}
	}

	public void addChild(String childName, String childIdNumber) {
		if (childName != null && !childName.isEmpty() && childIdNumber != null && !childIdNumber.isEmpty()) {
			childNames.add(childName);
			childIdNumbers.add(childIdNumber);
		} else {
			throw new IllegalArgumentException("Child name dan child ID number tidak boleh kosong.");
		}
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
