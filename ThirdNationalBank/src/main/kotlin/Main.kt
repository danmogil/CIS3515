fun main(args: Array<String>) {

    println("Welcome to Third National Bank!")

    val bankAccount = SavingsAccount("123ABC", "Kevin", 450.00)

    println("Account holder name: ${bankAccount.name}")
    println("Balance: ${bankAccount.balance}")

    // update balance
    bankAccount.deposit(100.00)
    bankAccount.withdraw(10.00)

    println("New balance: ${bankAccount.balance}")
}