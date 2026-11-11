public class Secrets {
    public static int shiftBack(int value, int amount) {
        // Shift bits to the right, and shift zeros in to the leftmost position.
        // Hence use the unsigned right shift operator (>>>).
        return value >>> amount;
    }

    public static int setBits(int value, int mask) {
        // A bit from value should be set to 1 where the bit in the mask is also 1.
        // All other bits should be kept unchanged.
        // This is equivalent to an OR operation (|).        
        return value | mask;
    }

    public static int flipBits(int value, int mask) {
        // If the bit is 1 in the mask, the bit is flipped in the value. All other bits are kept unchanged.
        // This is equivalent to the XOR operation (^).        
        return value ^ mask;
    }

    public static int clearBits(int value, int mask) {
        // The bits in the value should be set to 0 where the bit in the mask is 1.
        // All other bits should be kept unchanged.
        // By negating the mask so 1s become 0s, the AND operator (&) will produce 0 bits.
        return  value & ~mask;    
    }
}