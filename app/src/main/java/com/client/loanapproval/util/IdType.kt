package com.client.loanapproval.util

/**
 * @param: (String)text
 *
 * This enum class is used for Id card type
 */
enum class IdType(val text: String) {
    AADHAR_CARD("Aadhar card"),
    VOTER_CARD("Voter card"),
    PAN_CARD("Pan card"),
    DRIVING_LICENSE("Driving license"),
    PASSPORT("Passport")
}