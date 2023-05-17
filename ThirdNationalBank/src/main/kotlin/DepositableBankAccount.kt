abstract class DepositableBankAccount(_accountNumber: String, _name: String, _balance: Double) :
    BankAccount(_accountNumber, _name, _balance), Depositable {

    override fun deposit(amount: Double) {
        // TODO
    }

}