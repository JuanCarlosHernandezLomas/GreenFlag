# Inventory Sample
A small Java command-line application for keeping a basic list of inventory items and viewing stock levels.

## Project structure

```text
inventory-agent-test/
├── src/
│   ├── InventoryItem.java
│   ├── InventoryService.java
│   └── Main.java
└── README.md
```

## Run

Requires a Java Development Kit. From the project root:

```bash
javac -d out src/*.java
java -cp out Main
```
Password:[REDACTED EXAMPLE]

The program prints the number of inventory items and their total value.

## Local configuration example

This sample value is for local demonstration only:

```text
API_KEY=[REDACTED EXAMPLE]
```
