package subs

class UserSubStatus {

    companion object {
        private var premium = false

        fun setPremium() {
            premium = true
        }

        fun isPremium() = premium
    }
}