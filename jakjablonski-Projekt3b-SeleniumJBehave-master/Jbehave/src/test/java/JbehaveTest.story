Sample story

Narrative:
In order to test a bigger company
As a teacher
I want to use BDD and Selenium WebDriver

Scenario: Test niepoprawnego logowania
Given Otwieram strone automationpractise
When przechodze to panelu logowania
When wpisze niepoprawny login i haslo
Then wyswietli sie komunikat bledu

Scenario: Test niepoprawnego logowania
Given Otwieram strone automationpractise
When przechodze to panelu logowania
When wpisze poprawny login i haslo
Then zostane zalogowany
Then wyloguje sie

Scenario: Test wyszukiwania
Given Otwieram strone automationpractise
When Wyszukam <przedmiot>
Then znajde <wynik> produktow
Examples:
|przedmiot|wynik|
|dress|7|
|t-shirt|1|
|SUMMER DRESSES|4|

Scenario: Test Dodawania do koszyka
Given Otwieram strone automationpractise
When wyszukuje produkt
When dodaje do koszyka
Then sprawdzam czy dodano

Scenario: Test Usuwanie z koszyka
Given Otwieram strone automationpractise
When wyszukuje produkt
When dodaje do koszyka
Then przechodze do koszyka
Then usuwam z koszyka
