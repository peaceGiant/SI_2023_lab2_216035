Пеце Георгиев 216035

# Control Flow Graph
![CFG_216035](https://github.com/peaceGiant/SI_2023_lab2_216035/assets/60519243/1edecbe8-9b93-4cbf-91ff-94d3d6ee68ed)

# Цикломатска комплексност
За одредување на цикломатската комплексност на дадениот код ќе ја користиме формулата $`P+1`$, каде $`P`$ е бројот на предикатни јазли во CFG. За дадениот код, $`P=10`$ (јазлите означени со црвено), па цикломатската комплексност на кодот е 11.

# Тестови според Every Branch Criteria
Се забележува дека подпатиштата составени од ребрата 37-75, 63-75, 65-66-74-75, 67.2-74-75 и 69-75 се независни, односно не постои начин еден пат да содржи два од претходните наведени подпатишта. Ако тоа е така, од потреба ни се барем 5 тест случаи за да ги изминеме сите ребра. Ќе покажеме дека 5 е минималниот број тестови со помош на следната табела:

![ApplicationFrameHost_lzxnvvkvff](https://github.com/peaceGiant/SI_2023_lab2_216035/assets/60519243/e5c6ab0c-f91e-49ee-b232-938fac1d7d3a)

Да дадеме кратко образложение на тест случајот кој изминува најмногу ребра.
Повикот на функцијата `SILab2.function(new User(null, "pass#word", "A@email.com"), new ArrayList<User> (Arrays.asList(new User("Unique", "valid!Pass", "B@gmail.com"), new User("A@email.com", "random!Pass", "A@email.com"))))`
во целост ги изминува сите ребра во `for` јамката на линија 47 со точно две изминувања. Првиот пат не влегува во ниеден од вгнездените `if` контролни структури бидејќи мејл адресата и корисничкото име не се совпаѓа со првиот корисник во листата, што не е случај во втората итерација кога имаме поклопување на корисничките имиња/мејлови.
На сличен принцип се изминува `for` јамката на линија 67 каде во првата итерација, лозинката на корисникот не содржи специјален знак, имено `!`, наспроти втората итерација каде лозинката содржи специјален знак односно `#`.

# Тестови според Multiple Condition
Да го разгледаме кодот `if (user==null || user.getPassword()==null || user.getEmail()==null)`.
Можни се 4 ситуации:

1) `TXX`: Првата логичка променлива се евалуира во точно. Пример за тест случај: `SILab2.function(null, null)`;
2) `FTX`: Првата логичка променлива се евалуира грешно, а втората точно. Пример: `SILab2.function(new User("abc", null, "abc"), null)`;
3) `FFT`: Само третата логичка променлива е точна. Пример: `SILab2.function(new User("abc", "abc", null), null)`;
4) `FFF`: Сите логички променливи се неточни. Пример: `SILab2.function(new User("abc", "abc", "abc"), null)`;

Следува дека се потребни барем 4 тест случаи за да се разгледаат сите сценарија.

# Unit тестови
Согласно претходните дискусии, ги имаме следните тестови:
```java
    @Test
    void testEveryBranch() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> SILab2.function(null, null));
        assertTrue(e.getMessage().contains("Mandatory information missing!"));
        assertFalse(SILab2.function(new User("A", "A", "B"), null));
        assertFalse(SILab2.function(
                new User(null, "pass#word", "A@email.com"),
                new ArrayList<User>(Arrays.asList(
                        new User("Unique", "valid!Pass", "B@gmail.com"),
                        new User("A@email.com", "random!Pass", "A@email.com")
        ))));
        assertFalse(SILab2.function(new User("a", "bcdef ghi", "a-"), null));
        assertFalse(SILab2.function(new User("a", "bcdefghi", "a-"), null));
    }

    /**
     * Testing Multiple Conditions on the following if statement in SILab2.java:
     * if (user==null || user.getPassword()==null || user.getEmail()==null)
     */
    @Test
    void testMultipleConditions() {
        RuntimeException e = assertThrows(
                RuntimeException.class,
                () -> SILab2.function(null, null));
        assertTrue(e.getMessage().contains("Mandatory information missing!"));
        e = assertThrows(
                RuntimeException.class,
                () -> SILab2.function(new User("abc", null, "abc"), null));
        assertTrue(e.getMessage().contains("Mandatory information missing!"));
        e = assertThrows(
                RuntimeException.class,
                () -> SILab2.function(new User("abc", "abc", null), null));
        assertTrue(e.getMessage().contains("Mandatory information missing!"));
        assertFalse(SILab2.function(new User("abc", "abc", "abc"), null));
    }
```
