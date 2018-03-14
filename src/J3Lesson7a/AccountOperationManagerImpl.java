package J3Lesson7a;

public class AccountOperationManagerImpl implements AccountOperationManager {
    @BankingAnnotation(securityLevel = SecurityLevelEnum.HIGH)
    public double depositInCash(int accountNumber, int amount) {
// зачисление на депозит
        return 0; // stub
    }
    @BankingAnnotation(securityLevel = SecurityLevelEnum.HIGH)
    public boolean withdraw(int accountNumber, int amount) {
// снятие суммы, если не превышает остаток
        return true; // stub
    }
    @BankingAnnotation(securityLevel = SecurityLevelEnum.LOW)
    public boolean convert(double amount) {
// конвертировать сумму
        return true; // stub
    }
    @BankingAnnotation
    public boolean transfer(int accountNumber, double amount) {
// перевести сумму на счет
        return true; // stub
    }
}
