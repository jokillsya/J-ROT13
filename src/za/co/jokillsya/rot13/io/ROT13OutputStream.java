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

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import za.co.jokillsya.rot13.ROT13;

/**
 * The ROT13OutputStream filters all data through the ROT13 Algorithm
 *  before flushing.
 * @author Johnathan Botha
 */
public class ROT13OutputStream extends FilterOutputStream {

    public ROT13OutputStream( final OutputStream out ) {

        super(out);

    }

    @Override
    public void write( final int b ) throws IOException {

        out.write(ROT13.rot13(b));

    }

    @Override
    public void write( final byte b[] ) throws IOException {

        write(b, 0, b.length);

    }

    @Override
    public void write(
            final byte b[],
            final int off,
            final int len) 
                throws IOException {

        if ( (off | len | (b.length - (len + off)) | (off + len)) < 0 ) {

            throw new IndexOutOfBoundsException();

        }

        for ( int i = 0; i < len; i++ ) {

            write(b[off + i]);

        }

    }

}
