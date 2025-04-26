# Health-Hub-Web-Application-Selenium-Project

# Health Hub Automation Testing Framework

![Selenium](https://img.shields.io/badge/Selenium-4.1.2-green)
![TestNG](https://img.shields.io/badge/TestNG-7.4.0-red)
![ExtentReports](https://img.shields.io/badge/ExtentReports-5.0.9-blue)

## 📌 Overview
Automation testing framework for Health Hub web application using:
- **Selenium WebDriver** for browser automation
- **TestNG** as testing framework
- **ExtentReports** for interactive reporting
- **Page Object Model** design pattern

## 🚀 Features
- Cross-browser testing (Chrome/Firefox/Edge)
- Headless execution support
- Automated screenshot capture on failures
- Detailed HTML reports with historical data
- Configurable test parameters

## 📂 Project Structure
```
health-hub-automation/
├── src/test/
│   ├── java/
│   │   ├── base/              # Core framework classes
│   │   │   └── BaseTest.java
│   │   ├── pages/             # Page objects
│   │   │   ├── HomePage.java
│   │   │   └── LoginPage.java
│   │   ├── tests/             # Test scripts
│   │   │   ├── LoginTest.java
│   │   │   └── CartTest.java
│   │   └── utils/             # Utilities
│   │       └── ConfigReader.java
│   └── resources/
│       └── config.properties  # Configuration
├── drivers/                   # Browser drivers
├── test-output/               # Generated reports
├── Screenshots/               # Failure screenshots
├── testng.xml                 # Test suite config
└── pom.xml                    # Maven dependencies
```

## 🛠 Setup Instructions

### Prerequisites
- Java JDK 11+
- Maven 3.6+
- Chrome/Firefox browsers

### Installation
1. Clone repository:
   ```bash
https://github.com/Polo1996134/Health-Hub-Web-Application-Selenium-Project.git
   ```
2. Download browser drivers:
   - [ChromeDriver](https://chromedriver.chromium.org/)
   - [GeckoDriver](https://github.com/mozilla/geckodriver)
   - Place in `drivers/` folder

3. Configure environment:
   ```bash
   cp src/test/resources/config.example.properties src/test/resources/config.properties
   ```

## 🏃 Running Tests

### Run all tests:
```bash
mvn clean test
```

### Run specific test group:
```bash
mvn test -Dgroups=smoke
```

### Run with different browser:
```bash
mvn test -Dbrowser=firefox
```

### Run in headless mode:
```bash
mvn test -Dheadless=true
```

## 📊 Reports
After execution, view reports at:
```
test-output/HealthHubReport_<timestamp>.html
```

Sample report:
![Extent Report Screenshot](https://i.imgur.com/Jb6GX7y.png)

## ⚙ Configuration
Edit `config.properties`:
```properties
# Base URL
baseUrl=https://health-hub-rust.vercel.app

# Browser options
browser=chrome
headless=false

# Timeouts (seconds)
implicitWait=10
pageLoadTimeout=20

# Environment
env=staging
```

## 🧪 Test Cases
| Module       | Test Class        | Description                          |
|--------------|-------------------|--------------------------------------|
| Authentication | `LoginTest`      | Valid/invalid login scenarios        |
| Product Catalog | `ProductTest`    | Search and filter products           |
| Shopping Cart | `CartTest`       | Add/remove items from cart           |

## 🐛 Troubleshooting
- **Browser compatibility issues**: Ensure driver versions match browser versions
- **Element not found**: Check locators in page objects
- **Report not generating**: Verify ExtentReports dependencies in pom.xml

## 🤝 Contribution
1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

