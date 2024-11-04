# -------------------------------
# Ramiro Alejandro Gioia
# Project Name: [AUTOMATION FOR SMART EQUIP]
# -------------------------------

## Steps to Setup and Run

1. **Clone the Repository**:
   ```bash
   git clone [---]

2. **Prepare Your Local Java Environment**:
    - Install JDK (Java 8 or higher)
    - Install Maven
    - Ensure your JAVA_HOME environment variable is set.

3. **Download Dependencies: Run Maven to download all the corresponding dependencies**:
   ```bash
   mvn clean install

4. **Compile the Project**:
   ```bash
   mvn package

5. **Run All Existing Test Cases**:
   ```bash
   mvn test



1- Clone the following repository.
git clone ---

2- Prepare your local Java Environment: 
install JDK, Maven, etc.

3- Run Maven to download all the corresponding dependencies.
mvn clean install

5- Compile the project:
mvn package

6- Run all the existing test cases:
mvn test


# -------------------------------
# Ramiro Alejandro Gioia
List of Cases Developed for This Demo

**testSearchForAndroid()**:
- Verifies the search functionality by searching for "android".
- Asserts that the search results page is displayed and that the search term is present in the results.

**testAllRegionsMenuOptions()**:
- Navigates to the regions dropdown on the search results page.
- Clicks the dropdown and checks that the expected options are displayed.

**testDuckDuckGoApiIconUrl()**:
- Makes a request to the DuckDuckGo API for the query "Simpson".
- Asserts that the returned "RelatedTopics" are not null and prints the icon URLs for each topic found.