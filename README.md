## Pull Request Review Agent

This project provides an automated GitHub Pull Request reviewer built with Java 21 and Spring Boot.

### Features

- Analyzes changes submitted through Pull Requests.
- Calculates an approval score from 0% to 100%.
- Publishes recommended changes when the score is below 80%.
- Requests user confirmation before approving eligible Pull Requests.

### Requirements

- Java 21
- Maven 3.9+
- OpenAI API key
- GitHub App credentials