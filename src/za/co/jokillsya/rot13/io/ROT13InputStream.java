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
package za.co.jokillsya.rot13.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import za.co.jokillsya.rot13.ROT13;

/**
 * The ROT13InputStream filters all data through the ROT13 Algorithm.
 * @author Johnathan Botha
 */
public class ROT13InputStream extends FilterInputStream {

    public ROT13InputStream( final InputStream in ) {

        super(in);

    }

    @Override
    public final int read() throws IOException {

        return ROT13.rot13(in.read());

    }

    @Override
    public final int read( final byte[] b ) throws IOException {

        return read(b, 0, b.length);

    }

    @Override
    public final int read(
            final byte b[],
            final int off,
            final int len)
                throws IOException {

        if ( b == null ) {

            throw new NullPointerException();

        } else if ( off < 0 || len < 0 || len > b.length - off ) {

            throw new IndexOutOfBoundsException();

        } else if ( len == 0 ) {

            return 0;

        }

        byte c = (byte) ROT13.rot13(in.read());

        if ( -1 == c ) {

            return -1;

        }

        b[off] = c;

        int i = 1;

        try {

            for ( ; i < len; i++ ) {

                if ( -1 == ( c = (byte) ROT13.rot13(in.read()) ) ) {

                    return i;

                }

                b[off + i] = c;

            }

        } catch (IOException ee) {
            /**
             * Not needed...
             */
        }

        return i;

    }

}
