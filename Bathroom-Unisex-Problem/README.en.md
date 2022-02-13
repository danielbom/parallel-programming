# The Unisex Bathroom Problema

Java project with solution of the unisex bathroom synchronization problema, presented as an activity of the Concurrency Programing course at UTFPR.

## Description

***

The bathroom unisex problem comes from one story of a woman working far way of female bathroom. The closest bathroom was reserved for men. Then, she asked your boss to make the closest bathroom a unisex bathroom. The order has been accepted with some restrictions:

- The bathroom must contain only women or men, never both at the same time.
- The bathroom must have a limit of 3 peoples, to avoid employees 'kill time' with friends.

The solution to the problem can be achieved with a lot of synchronization methods. In this project, you will find a version with monitors and a version with semaphores, not identicals. The version of the monitor has a gender interleaving agreement. e.g. After a group of men goes to the bathroom, the next group will be of women if exists women. In the version of semaphores, the agreement is of justice, i.e. the order has importance. It's possible has women groups followed by another women group, or then when a group does not reach the allowed limit, it's consumed in fragmented way. Execute the intercalation, consecutive and 2/1 proportion test to understand better.

## Codification

***

The implementation with monitors was more natural to me. When use monitors, technically are possible to think straight and procedure form of the problem, create only variables of control and use thems. By the way, the code becomes denser. The monitors control variables are:

```java
int women = 0; // Counter of women who are using the bathroom
int men = 0;   // Counter of men who are using the bathroom

int womenWaiting = 0;  // Counter of women who are waiting
int menWaiting = 0;    // Counter of men who are waiting

Sex priority = Sex.FEMALE;  // Sexo prioritário atual

int consecutive = 0;  // Counter of consecutive peoples

// Initialized in constructor
int maxConsecutive;   // Limit of consecutive peoples allowed
```

To change priority is necessary to restart the counter of consecutive peoples, then I created a method to do this.

```java
private void changePriority(Sex sex) {
    priority = sex;
    consecutive = 0;
}
```

The method to enter the bathroom must be atomic, achieved using the modifier *__synchronized__*. This makes it impossible to guarantee the ordering to use the bathroom, however the implementation becomes simpler. This does impossible gathering to order to use the bathroom, however, the implementation becomes more simple. The method uses information about the sex of a person wishing to enter the bathroom to apply the required rules.

When a person enters the bathroom, immediately it's incremented the counter of persons waiting with your sex **(womenWaiting++, menWaiting++)**. In sequence, the rules of the problem are checked. e.g. A woman can use a bathroom if priority is female, the amount of women in bathroom is smaller the limit allowed and the amount of men in the bathroom has to be zero **(Wait if => priority == Sex.MALE || women > limit || men != 0)**. In case of not exists man waiting, the number of men in the bathroom is 0, and the limit does not have been achieved, the woman can alter the priority to female and exit of the waiting loop **(menWaiting == 0 && women <= limit && men == 0)**. Immediately in the exit of the waiting loop, it's decremented the counter of persons waiting with your sex **(womenWaiting--, menWaiting--)**.

Besides, it's incremented the counter of the person using the bathroom of the sex of the person, and the counter of a consecutive person **(women++, men++, consecutive++)**. In the end of the method, it checks if the number of consecutive persons achieved the limit to change the priority **(consecutive >= maxConsecutive)**. The same logic is applied to men.

```java
public synchronized void enterBathroom(Person person) {
    int limit = maxConsecutive - 1;
    try {
        switch (person.getSex()) {
            case FEMALE:
                womenWaiting++;
                while (priority == Sex.MALE || women > limit || men != 0) {
                    if (menWaiting == 0 && women <= limit && men == 0) {
                        changePriority(Sex.FEMALE);
                        break;
                    }
                    this.wait();
                }
                womenWaiting--;

                women++;
                consecutive++;

                if (consecutive >= maxConsecutive)
                    changePriority(Sex.MALE);
                break;
            case MALE:
                menWaiting++;
                while (priority == Sex.FEMALE || men > limit || women != 0) {
                    if (womenWaiting == 0 && men <= limit && women == 0) {
                        changePriority(Sex.MALE);
                        break;
                    }
                    this.wait();
                }
                menWaiting--;

                men++;
                consecutive++;

                if (consecutive == maxConsecutive)
                    changePriority(Sex.FEMALE);
                break;
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```

The method to exit the bathroom is very simple. It's decremented the counter of the person using the bathroom of the sex of the person **(women--, men---)**. The last to go out notify all about the bathroom.

```java
public synchronized void exitBathroom(Person person) {
    switch (person.getSex()) {
        case FEMALE:
            women--;
            if (women == 0) this.notifyAll();
            break;
        case MALE:
            men--;
            if (men == 0) this.notifyAll();
            break;
    }
}
```

### Semaphores

***

The implementation with semaphores was based on the pseudocode of a youtube video. This implementation is confusing to me, however, I can understand the exercise. The semaphore control variables are:

```java
Semaphore empty = new Semaphore(1); // Guarantees mutual exclusion between genders

Semaphore maleMutex = new Semaphore(1); // Guarantees mutual exclusion between men
Semaphore male; // Limiting men | Initialized in constructor

Semaphore femaleMutex = new Semaphore(1); // Guarantees mutual exclusion between women
Semaphore female; // Limiting women | Initialized in constructor

Semaphore order = new Semaphore(1, true); // Guarantees arrive order

int femaleCount = 0; // Counter of women
int maleCount = 0; // Counter of men
```

To enter the bathroom, we can observe how the intercalation between the semaphores solves the problem. Immediately on entering the bathroom, the semaphore **order** is used to ensure mutual exclusion, working similar to *__synchronized__*. Also, cause the flag **fair** is activated it's responsible to ensure ordination by arrival too.

Like the monitor's version, it's necessary to check person gender wishing use bathroom. e.g. One woman wishing to enter, the woman mutex is used **(femaleMutex.acquire())**. In sequence, case it's the first one entering, the semaphore **empty** who guarantees mutual exclusion between genders is used **(empty.acquire())**. And then, it's increment the counter of women **(femaleCount++)**, and the woman mutex is released **(femaleMutex.release())**. Before finishing, the mutex of woman's limiting is used **(female.acquire())**. The same logic is applied to persons of the male sex.

```java
public void enterBathroom(Person person) {
    try {
        order.acquire();
        switch (person.getSex()) {
        case FEMALE:
            femaleMutex.acquire();

            if (femaleCount == 0) empty.acquire();
            femaleCount++;

            femaleMutex.release();

            female.acquire();
            break;
        case MALE:
            maleMutex.acquire();

            if (maleCount == 0) empty.acquire();
            maleCount++;

            maleMutex.release();

            male.acquire();
            break;
        }
        order.release();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```

To exit of bathroom it's necessary release mutex of genders's limiting **(female.release(), male.release())**. It's updated the counter to person exiting **(femaleCount--, maleCount--)** and release the **empty** semaphore, if it's the last person of group exiting **(empty.release())**. This operation is exclusive, achieved by each gender's mutex **(femaleMutex.acquire(), femaleMutex.release(), maleMutex.acquire(), maleMutex.release())**.

```java
public void exitBathroom(Person person) {
    try {
        switch (person.getSex()) {
        case FEMALE:
            female.release();

            femaleMutex.acquire();
            femaleCount--;
            if (femaleCount == 0) empty.release();

            femaleMutex.release();
            break;
        case MALE:
            male.release();

            maleMutex.acquire();
            maleCount--;
            if (maleCount == 0) empty.release();

            maleMutex.release();
            break;
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```

## Conclusion

***

The semaphore's version seems to offer a solution simpler, but it's necessary knows about semaphore's properties and concepts. On the other hand, the monitor's version despite your complexity, the construction of logic on problem not require of how the synchronization tool works.

Both resolutions respects the problem's requirements, but each one have own particular caracteristics, which I can't imagine how do it's in your opositor. e.g. Ensure ordering to queue it's a interested feature in this context, and it's not fair no have one. This feature has been achieved with a fair semaphore in semaphore's version, but I can't imagine how apply this in monitor's version.
