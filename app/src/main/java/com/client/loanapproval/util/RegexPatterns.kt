package com.client.loanapproval.util

/**
 * This is an object class contains All regex patterns that are used for Validate id card type
 */
object RegexPatterns {
    const val pan_card = "^[A-Z]{3}[ABCFGHLJPTF]{1}[A-Z]{1}[0-9]{4}[A-Z]{1}$";
    const val voter_id_card = "^[a-zA-Z]{3}[0-9]{7}$";
    const val aadhar_card = "^[0-9]{12}$";
    const val driving_license = "^(([A-Z]{2}[0-9]{2})( )|([A-Z]{2}-[0-9]{2}))((19|20)[0-9][0-9])[0-9]{7}$";
    const val passport = "^[A-PR-WYa-pr-wy][1-9]\\d\\s?\\d{4}[1-9]$";
    const val email = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"
    const val empty = ""
}