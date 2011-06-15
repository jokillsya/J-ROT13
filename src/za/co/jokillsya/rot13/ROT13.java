/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package za.co.jokillsya.rot13;

/**
 * A simple ROT13 substitution cipher class.
 * 
 * @author Johnathan Botha
 */
public final class ROT13 {

    //Basic constants
    private static final int A      = 65; //Int value for A
    private static final int Z      = 90; //Int value for Z
    private static final int ALP    = 26; //Number of letters in the Alphabet
    private static final int H_ALP  = 13; //Half of 26 (no shit!)
    private static final int MASK   = 32; //cap bitmask
    private static final int CALC_1 = 65 - H_ALP;

    private ROT13() {
        /**
         * Suppressed
         */
    }

    /**
     * Performs a ROT13 transformation on an int value.
     * @param anInt The int to transform
     * @return <code>int</code> - The transformed int.
     */
    public static final int rot13( int anInt ) {

        final int cap = anInt & MASK;

        //int re-assignment done to avoid the overhead of an interim value
        anInt &= ~cap;

        if ((anInt >= A) && (anInt <= Z)) {

            return ((anInt - CALC_1) % ALP + A) | cap;

        }

        return anInt | cap;

    }

    /**
     * Performs a ROT13 transformation on a char value.
     * @param ch The character to transform
     * @return <code>char</code> - The transformed character.
     */
    public static final char rot13( final char ch ) {

        final int c = ch;
        
        return (char) rot13(c);

    }

    /**
     * Performs a ROT13 transformation on a subset of an <code>int[]</code>.
     * <BR/>
     * The subset is defined as the range between the
     * <code>stIdx</code> and <code>enIdx</code> arguments.
     * <BR/>
     * The resulting array is stored in the <code>dst</code> array passed in
     * as an argument.
     * @param src The source <code>int[]</code>.
     * @param dst The destination <code>int[]</code>.
     * @param stIdx The start index
     * @param enIdx The end index
     * @throws IndexOutOfBoundsException if the indices passed as arguments
     * are not within bounds.
     */
    public static final void rot13(
            final int[] src,
            final int[] dst,
            final int stIdx,
            final int enIdx)
            throws IndexOutOfBoundsException {

        checkIndices(stIdx, enIdx, src.length, dst.length);

        if ( stIdx == enIdx ) {

            dst[stIdx] = rot13(src[stIdx]);

            //No need to hit for loop.
            return;

        }

        
        //Rotate each integer within bounds
        for ( int x = enIdx; --x >= stIdx; ) {

            dst[x] = rot13(src[x]);

        }

    }

    /**
     * Performs a ROT13 transformation on an <code>int[]</code>.
     * <BR/>
     * The resulting array is stored in the <code>dst</code> array passed in
     * as an argument.
     * @param src The source <code>int[]</code>.
     * @param dst The destination <code>int[]</code>.
     */
    public static final void rot13( final int[] src, final int[] dst )
            throws IndexOutOfBoundsException {

        rot13(src, dst, 0, src.length);

    }

    /**
     * Performs a ROT13 transformation on an <code>int[]</code>.
     * <BR/>
     * The resulting array is stored in the <code>dst</code> array passed in
     * as an argument.
     * @param src The source <code>int[]</code>.
     * @return int[] - The transformed int array
     */
    public static final int[] rot13( final int[] src ) {

        final int[] dst = new int[src.length];

        rot13(src, dst);

        return dst;

    }

    /**
     * Performs a ROT13 transformation on a subset of a <code>char[]</code>.
     * <BR/>
     * The subset is defined as the range between the
     * <code>stIdx</code> and <code>enIdx</code> arguments.
     * <BR/>
     * The resulting array is stored in the <code>dst</code> array passed in
     * as an argument.
     * @param src The source <code>char[]</code>.
     * @param dst The destination <code>char[]</code>.
     * @param stIdx The start index
     * @param enIdx The end index
     * @throws IndexOutOfBoundsException if the indices passed as arguments
     * are not within bounds.
     */
    public static final void rot13(
            final char[] src,
            final char[] dst,
            final int stIdx,
            final int enIdx)
            throws IndexOutOfBoundsException {

        checkIndices(stIdx, enIdx, src.length, dst.length);

        if (stIdx == enIdx) {

            dst[stIdx] = rot13(src[stIdx]);

            //No need to hit loop.
            return;

        }

        //Rotate each character within bounds
        for ( int x = enIdx; --x >= stIdx; ) {

            dst[x] = rot13(src[x]);

        }

    }

    /**
     * Performs a ROT13 transformation on a <code>char[]</code>.
     * <BR/>
     * The resulting array is stored in the <code>dst</code> array passed in
     * as an argument.
     * @param src The source <code>char[]</code>.
     * @param dst The destination <code>char[]</code>.
     */
    public static final void rot13( final char[] src, final char[] dst )
            throws IndexOutOfBoundsException {

        rot13(src, dst, 0, src.length);

    }

    /**
     * Performs a ROT13 transformation on a <code>char[]</code>.
     * <BR/>
     * The resulting array is stored in the <code>dst</code> array passed in
     * as an argument.
     * @param src The source <code>char[]</code>.
     * @return char[] - The transformed char array
     */
    public static final char[] rot13( final char[] src ) {

        final char[] dst = new char[src.length];

        rot13(src, dst);

        return dst;

    }

    /**
     * Performs a ROT13 transformation on a <code>String</code>.
     * <BR/>
     * @param text The source <code>String</code>.
     * @return String - The transformed <code>String</code>.
     */
    public static final String rot13( final String text ) {

        final char[] dst = new char[text.length()];

        text.getChars(0, text.length(), dst, 0);

        rot13(dst, dst);

        return new String(dst);

    }

    /**
     * Ensure that the indices are within bounds.
     * 
     * @param stIdx
     * @param enIdx
     * @param srcLen
     * @param dstLen
     * @throws IndexOutOfBoundsException
     */
    private static final void checkIndices(
            final int stIdx,
            final int enIdx,
            final int srcLen,
            final int dstLen)
            throws IndexOutOfBoundsException {

        if (stIdx < 0) {

            throw new IndexOutOfBoundsException(Integer.toString(stIdx));

        }

        if (enIdx < 0) {

            throw new IndexOutOfBoundsException(Integer.toString(enIdx));

        }

        if (stIdx > enIdx) {

            throw new IndexOutOfBoundsException(Integer.toString(enIdx - stIdx));

        }

        if ((stIdx > srcLen) || (stIdx > dstLen)) {

            throw new IndexOutOfBoundsException(Integer.toString(stIdx));

        }

        if ((enIdx > srcLen) || (enIdx > dstLen)) {

            throw new IndexOutOfBoundsException(Integer.toString(enIdx));

        }

    }
    
}
