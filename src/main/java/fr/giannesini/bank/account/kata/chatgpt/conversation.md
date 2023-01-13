# BankAccountKata2021

Implémentation bank account kata par chat gpt

## Définition Kata

> Est-ce que tu connais le kata bank account ?

> Oui, je connais le kata Bank Account. Il s'agit d'un exercice de programmation qui consiste à créer une classe de
> compte bancaire qui peut effectuer des opérations de base telles que des dépôts, des retraits et des vérifications de
> solde. Il est souvent utilisé pour enseigner les concepts de base de la programmation orientée objet.

## Représentation Object

> est ce que tu peux faire un design objet de ce kata ?

> Oui, voici un exemple possible de design objet pour le kata Bank Account:
>> Classe BankAccount: représente un compte bancaire. Elle contient les attributs suivants:<br>
- balance: représente le solde actuel du compte.<br>
- transactions: représente l'historique des transactions effectuées sur le compte.<br>
>
>> Méthodes de la classe BankAccount:<br>
- deposit(amount: float): effectue un dépôt sur le compte.<br>
- withdraw(amount: float): effectue un retrait sur le compte.<br>
- get_balance(): retourne le solde actuel du compte.<br>
- get_transactions(): retourne l'historique des transactions effectuées sur le compte.<br>
>
>> Classe Transaction: représente une transaction bancaire. Elle contient les attributs suivants :<br>
- amount: représente le montant de la transaction.<br>
- timestamp : représente la date et l'heure de la transaction.<br>
- type : représente le type de transaction (deposit or withdrawal)<br>
>
>> Méthodes de la classe Transaction :<br>
- get_amount() : retourne le montant de la transaction.<br>
- get_timestamp() : retourne la date et l'heure de la transaction.<br>
- get_type() : retourne le type de transaction.<br>

> Notez que ceci est un exemple de design objet possible pour le kata Bank Account. Il existe de nombreuses autres
> façons de l'implémenter.

## Implémenation Java

> Peux tu me montrer l'implémentation en java de BankAccount ?

```
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
private float balance;
private List<Transaction> transactions;

    public BankAccount() {
        this.balance = 0.0f;
        this.transactions = new ArrayList<>();
    }

    public void deposit(float amount) {
        this.balance += amount;
        this.transactions.add(new Transaction(amount, TransactionType.DEPOSIT));
    }

    public void withdraw(float amount) {
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance -= amount;
        this.transactions.add(new Transaction(amount, TransactionType.WITHDRAW));
    }

    public float getBalance() {
        return this.balance;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }
}
 ```

> Et voici l'implémentation de la classe Transaction :

 ```
import java.time.LocalDateTime;

public class Transaction {
private float amount;
private LocalDateTime timestamp;
private TransactionType type;

    public Transaction(float amount, TransactionType type) {
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }
}
 ```

> Et enfin la classe TransactionType pour identifier les types de transactions :

```
public enum TransactionType {
    DEPOSIT, WITHDRAW;
}
```

> Notez que ceci est un exemple simple d'implémentation de la classe BankAccount, il existe de nombreuses améliorations
> possibles pour cette implémentation (par exemple, vérifier que les entrées soient valides, gérer les erreurs, etc.).

## Tests unitaires

> Peux-tu me générer les tests unitaires avec Junit 5 ?

> Voici un exemple de tests unitaires pour la classe BankAccount en utilisant JUnit 5 :

```
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

class BankAccountTest {
    @Test
    void testDeposit() {
        BankAccount account = new BankAccount();
        account.deposit(100.0f);
        assertEquals(100.0f, account.getBalance());
    }

    @Test
    void testWithdraw() {
        BankAccount account = new BankAccount();
        account.deposit(100.0f);
        account.withdraw(50.0f);
        assertEquals(50.0f, account.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        BankAccount account = new BankAccount();
        account.deposit(100.0f);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(150.0f));
    }

    @Test
    void testGetTransactions() {
        BankAccount account = new BankAccount();
        account.deposit(100.0f);
        account.withdraw(50.0f);
        List<Transaction> transactions = account.getTransactions();
        assertEquals(2, transactions.size());
        assertEquals(TransactionType.DEPOSIT, transactions.get(0).getType());
        assertEquals(TransactionType.WITHDRAW, transactions.get(1).getType());
    }

    @Test
    void testGetTransactionTimestamp(){
        BankAccount account = new BankAccount();
        LocalDateTime before = LocalDateTime.now();
        account.deposit(100.0f);
        LocalDateTime after = LocalDateTime.now();
        List<Transaction> transactions = account.getTransactions();
        LocalDateTime timestamp = transactions.get(0).getTimestamp();
        assertTrue(timestamp.isAfter(before) || timestamp.isEqual(before));
        assertTrue(timestamp.isBefore(after) || timestamp.isEqual(after));
    }
}
```

> Ces tests vérifient le comportement de la classe BankAccount en utilisant des assertions pour vérifier que les valeurs
> retournées sont correctes. Il vérifie aussi si les exceptions sont levées lorsque c'est nécessaire. Il existe d'autres
> test qui pourraient être ajouté pour couvrir d'autres cas d'utilisation.

## Classes immutables

> Peux tu rendre les classes immutables ?

> Pour rendre les classes BankAccount et Transaction immuables, il faut les rendre "final", et remplacer les setters par
> des constructeurs ou des méthodes de factory. Il faut également rendre les attributs privés et "final".

Voici un exemple d'implémentation des classes BankAccount et Transaction en utilisant des classes immuables :

```
import java.util.Collections;
import java.util.List;

public final class BankAccount {
    private final float balance;
    private final List<Transaction> transactions;

    public BankAccount(float balance, List<Transaction> transactions) {
        this.balance = balance;
        this.transactions = Collections.unmodifiableList(transactions);
    }

    public BankAccount() {
        this(0.0f, Collections.emptyList());
    }

    public BankAccount deposit(float amount) {
        return new BankAccount(balance + amount, addTransaction(amount, TransactionType.DEPOSIT));
    }

    public BankAccount withdraw(float amount) {
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        return new BankAccount(balance - amount, addTransaction(amount, TransactionType.WITHDRAW));
    }

    public float getBalance() {
        return this.balance;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    private List<Transaction> addTransaction(float amount, TransactionType type) {
        List<Transaction> newTransactions = new ArrayList<>(transactions);
        newTransactions.add(new Transaction(amount, type));
        return newTransactions;
    }
}
```

```
import java.time.LocalDateTime;
import java.util.Objects;

public final class Transaction {
    private final float amount;
    private final LocalDateTime timestamp;
    private final TransactionType type;

    public Transaction(float amount, TransactionType type) {
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.type = Objects.requireNonNull(type);
    }

    public float getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }
}
```

> Notez que pour la classe Transaction les méthodes getters restent les même, pour la classe BankAccount les méthodes
> deposit et withdraw retourne des nouvelles instances de cette classe plutôt que de modifier l'instance existante.
> Ainsi, l'historique des transactions est devenu immuable, pour ajouter des transactions il faut créer une nouvelle
> instance de BankAccount avec cette transaction.
> Il est important de noter que pour rendre une classe immuable il faut s'assurer que toutes les instances qui lui sont
> liées le soient également, dans ce cas les instances de Transaction doivent

## Tests Classes immutables

> Peux-tu regénérer les tests pour ces nouvelles classes ?

> Voici un exemple de tests unitaires pour les nouvelles classes immuables BankAccount et Transaction en utilisant JUnit
> 5 :

```
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

class BankAccountTest {
    @Test
    void testDeposit() {
        BankAccount account = new BankAccount();
        BankAccount newAccount = account.deposit(100.0f);
        assertEquals(100.0f, newAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        BankAccount account = new BankAccount(100.0f, List.of());
        BankAccount newAccount = account.withdraw(50.0f);
        assertEquals(50.0f, newAccount.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        BankAccount account = new BankAccount(100.0f, List.of());
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(150.0f));
    }

    @Test
    void testGetTransactions() {
        BankAccount account = new BankAccount(100.0f, List.of());
        BankAccount newAccount = account.withdraw(50.0f);
        List<Transaction> transactions = newAccount.getTransactions();
        assertEquals(1, transactions.size());
        assertEquals(TransactionType.WITHDRAW, transactions.get(0).getType());
    }

    @Test
    void testGetTransactionTimestamp(){
        BankAccount account = new BankAccount();
        LocalDateTime before = LocalDateTime.now();
        account = account.deposit(100.0f);
        LocalDateTime after = LocalDateTime.now();
        List<Transaction> transactions = account.getTransactions();
        LocalDateTime timestamp = transactions.get(0).getTimestamp();
        assertTrue(timestamp.isAfter(before) || timestamp.isEqual(before));
        assertTrue(timestamp.isBefore(after) || timestamp.isEqual(after));
    }
}
```

> Il faut noter que les tests unitaires ont été adaptés aux nouvelles implémentations des classes BankAccount et
> Transaction qui sont immuables, ainsi les méthodes deposit et withdraw retournent une nouvelle instance de BankAccount
> plutôt que de modifier l'instance existante. Il faut donc vérifier les états des nouvelles instances crées pour les
> tests.

## Class amount

> Peux-tu remplacer la variable amount par une classe Amount ?

> Oui, pour remplacer la variable amount de la classe Transaction par une classe Amount, vous pouvez créer une nouvelle
> classe Amount qui contient la variable amount et des méthodes pour accéder à cette variable.
> Voici un exemple d'implémentation de la classe Amount :

```
public class Amount {
private final float amount;

    public Amount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }
}
```

> Ensuite, vous pouvez utiliser cette classe dans la classe Transaction:

```
import java.time.LocalDateTime;
import java.util.Objects;

public final class Transaction {
private final Amount amount;
private final LocalDateTime timestamp;
private final TransactionType type;

    public Transaction(Amount amount, TransactionType type) {
        this.amount = Objects.requireNonNull(amount);
        this.timestamp = LocalDateTime.now();
        this.type = Objects.requireNonNull(type);
    }

    public Amount getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }
}
```

## Mauvais Tests unitaires

> Peux-tu regénérer les tests unitaires ?

> Voici un exemple de tests unitaires pour les nouvelles classes Transaction et Amount en utilisant JUnit 5 :

```
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

class BankAccountTest {
    @Test
    void testAmount() {
        Amount amount = new Amount(100.0f);
        assertEquals(100.0f, amount.getAmount());
    }

    @Test
    void testTransaction() {
        Amount amount = new Amount(100.0f);
        LocalDateTime before = LocalDateTime.now();
        Transaction transaction = new Transaction(amount, TransactionType.DEPOSIT);
        LocalDateTime after = LocalDateTime.now();
        assertEquals(amount, transaction.getAmount());
        assertEquals(TransactionType.DEPOSIT, transaction.getType());
        LocalDateTime timestamp = transaction.getTimestamp();
        assertTrue(timestamp.isAfter(before) || timestamp.isEqual(before));
        assertTrue(timestamp.isBefore(after) || timestamp.isEqual(after));
    }
}
```

> Il faut noter que pour tester la classe Transaction on utilise la classe Amount, on vérifie que le montant est bien
> celui passé en paramètre, que le type est celui passé en paramètre et qu'on a bien récupérer le timestamp.
> Ces tests unitaires vérifient que les méthodes de la classe Transaction et Amount fonctionnent correctement en
> utilisant des assertions pour vérifier que les valeurs retournées sont correctes. Il pourrait y avoir d'autres tests à
> ajouter pour couvrir d'autres cas d'utilisation.

## Bons tests unitaires

> Peux-tu regénérer les tests de BankAccount ?

> Voici un exemple de tests unitaires pour la classe BankAccount en utilisant JUnit 5 et en utilisant la classe Amount :

```
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class BankAccountTest {
    @Test
    void testDeposit() {
        BankAccount account = new BankAccount();
        Amount amount = new Amount(100.0f);
        BankAccount newAccount = account.deposit(amount);
        assertEquals(amount.getAmount(), newAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        BankAccount account = new BankAccount(new Amount(100.0f), List.of());
        Amount amount = new Amount(50.0f);
        BankAccount newAccount = account.withdraw(amount);
        assertEquals(amount.getAmount(), newAccount.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        BankAccount account = new BankAccount(new Amount(100.0f), List.of());
        Amount amount = new Amount(150.0f);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(amount));
    }

    @Test
    void testGetTransactions() {
        BankAccount account = new BankAccount(new Amount(100.0f), List.of());
        Amount amount = new Amount(50.0f);
        BankAccount newAccount = account.withdraw(amount);
        List<Transaction> transactions = newAccount.getTransactions();
        assertEquals(1, transactions.size());
        assertEquals(TransactionType.WITHDRAW, transactions.get(0).getType());
    }
}
```

> Il faut noter que pour ces tests on utilise la classe Amount pour effectuer les dépôts et les retraits, et on vérifie
> que le balance retourné est bien celui qui est attendu. Il faut également adapter les tests pour vérifier que les
> transactions ont bien été ajoutées.
> Ces tests unitaires vérifient le comportement de la classe BankAccount en utilisant des assertions pour vérifier que les
> valeurs retournées sont correctes et qu'il lève les exceptions attendues

## Mise à jour de la classe BankAccount

> Peux-tu regénérer BankAccount ?

> Voici un exemple d'implémentation de la classe BankAccount en utilisant la classe Amount

```
import java.util.Collections;
import java.util.List;

public final class BankAccount {
    private final Amount balance;
    private final List<Transaction> transactions;

    public BankAccount(Amount balance, List<Transaction> transactions) {
        this.balance = balance;
        this.transactions = Collections.unmodifiableList(transactions);
    }

    public BankAccount() {
        this(new Amount(0.0f), Collections.emptyList());
    }

    public BankAccount deposit(Amount amount) {
        return new BankAccount(new Amount(balance.getAmount()+amount.getAmount()), addTransaction(amount, TransactionType.DEPOSIT));
    }

    public BankAccount withdraw(Amount amount) {
        if (amount.getAmount() > this.balance.getAmount()) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        return new BankAccount(new Amount(balance.getAmount() - amount.getAmount()), addTransaction(amount, TransactionType.WITHDRAW));
    }

    public float getBalance() {
        return this.balance.getAmount();
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    private List<Transaction> addTransaction(Amount amount, TransactionType type) {
        List<Transaction> newTransactions = new ArrayList<>(transactions);
        newTransactions.add(new Transaction(amount, type));
        return newTransactions;
    }
}
```

> Il faut noter que cette implémentation utilise la classe Amount pour gérer les montants, on utilise les méthodes de
> cette classe pour accéder aux montants et pour les calculs, on vérifie également que le solde est suffisant pour
> effectuer un retrait.
> Les méthodes deposit et withdraw retournent des nouvelles instances de BankAccount pour respecter l'immuabilité.