package lab_02;

public class task02_1 {
    public static void main(String[] args) {

        int a = 0x7FFF_FFFF;
        int b = 0x8000_0001;

        System.out.println("initial numbers:");
        System.out.println("a = " + a + " -> bin: " + Integer.toBinaryString(a));
        System.out.println("b = " + b + " -> bin: " + Integer.toBinaryString(b));
        System.out.println("--------------------------------------------------");

        int andResult = a & b;
        System.out.println("a & b   (AND):       " + Integer.toBinaryString(andResult));

        int orResult = a | b;
        System.out.println("a | b   (OR):        " + Integer.toBinaryString(orResult));

        int xorResult = a ^ b;
        System.out.println("a ^ b   (XOR):       " + Integer.toBinaryString(xorResult));

        int notA = ~a;
        int notB = ~b;
        System.out.println("~a      (NOT a):     " + Integer.toBinaryString(notA));
        System.out.println("~b      (NOT b):     " + Integer.toBinaryString(notB));

        int shiftLeftA = a << 1;
        int shiftLeftB = b << 1;
        System.out.println("a << 1  (Left):      " + Integer.toBinaryString(shiftLeftA));
        System.out.println("b << 1  (Left):      " + Integer.toBinaryString(shiftLeftB));

        int shiftRightA = a >> 1;
        int shiftRightB = b >> 1;
        System.out.println("a >> 1  (Right):     " + Integer.toBinaryString(shiftRightA));
        System.out.println("b >> 1  (Right):     " + Integer.toBinaryString(shiftRightB));

        int unsignedShiftA = a >>> 1;
        int unsignedShiftB = b >>> 1;
        System.out.println("a >>> 1 (Unsigned):  " + Integer.toBinaryString(unsignedShiftA));
        System.out.println("b >>> 1 (Unsigned):  " + Integer.toBinaryString(unsignedShiftB));
    }
}
