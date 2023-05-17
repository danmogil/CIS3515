class CheckingAccount(_accountNumber: String, _name: String, _balance: Double) :
    DepositableBankAccount(_accountNumber, _name, _balance) {

        val overdraft_amount = 50.0
        val overdraft_fee = 15.0

        override fun withdraw(amount: Double) {
            if(amount <= balance) super.withdraw(amount)
            else if(amount <= balance + overdraft_amount) super.withdraw(amount + overdraft_fee)
        }

}