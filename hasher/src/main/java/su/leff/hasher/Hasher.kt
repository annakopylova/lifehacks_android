package su.leff.hasher

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils


class Hasher {
    fun getHash(input: String): String {
        return String(Hex.encodeHex(DigestUtils.sha512(input)))
    }
}