package lib;

public class TaxFunction {

    // Konstanta untuk nilai-nilai bermakna
    private static final double TAX_RATE = 0.05;
    private static final int STANDARD_DEDUCTIBLE = 54000000;
    private static final int MARRIED_DEDUCTIBLE_INCREASE = 4500000;
    private static final int CHILD_DEDUCTIBLE_PER_CHILD = 1500000;
    private static final int MAX_CHILDREN_FOR_DEDUCTIBLE = 3;

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        int tax = 0;

        // Menghitung total penghasilan dan pengurangan
        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int totalDeductible = deductible + STANDARD_DEDUCTIBLE;

        // Menambahkan pengurangan tambahan untuk pasangan dan anak-anak
        if (isMarried) {
            totalDeductible += MARRIED_DEDUCTIBLE_INCREASE;
        }
        totalDeductible += Math.min(numberOfChildren, MAX_CHILDREN_FOR_DEDUCTIBLE) * CHILD_DEDUCTIBLE_PER_CHILD;

        // Menghitung pajak berdasarkan tarif dan penghasilan bersih setelah pengurangan
        tax = (int) Math.round(TAX_RATE * (totalIncome - totalDeductible));

        // Jika hasil pajak negatif, mengembalikan 0
        return Math.max(tax, 0);
    }
}

