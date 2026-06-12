package de.itsgraphax.fgtDiscovery.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * All functions here are literaly from the <a href="https://minecraft.wiki/w/Java_Edition_protocol/Packets#VarInt_and_VarLong">minecraft wiki</a>
 */
public final class PacketHelper {
    private static final int SEGMENT_BITS = 0x7F;
    private static final int CONTINUE_BIT = 0x80;

    public static int readVarInt(DataInputStream inStream) throws IOException {
        int value = 0;
        int position = 0;
        byte currentByte;

        while (true) {
            currentByte = inStream.readByte();
            value |= (currentByte & SEGMENT_BITS) << position;

            if ((currentByte & CONTINUE_BIT) == 0) break;

            position += 7;

            if (position >= 32) throw new RuntimeException("VarInt is too big");
        }

        return value;
    }

    /* casts for some reason int to long so is this required?
    public long readVarLong(DataInputStream inStream) throws IOException {
        long value = 0;
        int position = 0;
        byte currentByte;

        while (true) {
            currentByte = inStream.readByte();
            value |= (long) (currentByte & SEGMENT_BITS) << position;

            if ((currentByte & CONTINUE_BIT) == 0) break;

            position += 7;

            if (position >= 64) throw new RuntimeException("VarLong is too big");
        }

        return value;
    }*/

    public static void writeVarInt(DataOutputStream outStream, int value) throws IOException {
        while (true) {
            if ((value & ~SEGMENT_BITS) == 0) {
                outStream.writeByte(value);
                return;
            }

            outStream.writeByte((value & SEGMENT_BITS) | CONTINUE_BIT);

            // Note: >>> means that the leftmost bits are filled with zeroes regardless of the sign,
            // rather than being filled with copies of the sign bit to preserve the sign.
            // In languages that don't have a ">>>" operator, This behavior can often be selected by
            // performing the shift on an unsigned type.
            value >>>= 7;
        }
    }
    /* something doesent work here, is this required?
    public void writeVarLong(DataOutputStream outStream, long value) {
        while (true) {
            if ((value & ~((long) SEGMENT_BITS)) == 0) {
                outStream.writeByte(value);
                return;
            }

            outStream.writeByte((value & SEGMENT_BITS) | CONTINUE_BIT);

            // Note: >>> means that the leftmost bits are filled with zeroes regardless of the sign,
            // rather than being filled with copies of the sign bit to preserve the sign.
            // In languages that don't have a ">>>" operator, This behavior can often be selected by
            // performing the shift on an unsigned type.
            value >>>= 7;
        }
    }*/

}
