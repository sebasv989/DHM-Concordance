# Concordance Generator

This Java application generates a concordance for a given English text document. A concordance is an alphabetical list of all word occurrences, labeled with word frequencies and the sentence numbers in which each word appears.

## Features

- **Alphabetical Listing**: Generates an alphabetical list of all words in the document.
- **Word Frequencies**: Tracks how many times each word appears in the document.
- **Sentence Numbers**: Identifies the sentence numbers in which each word appears.

## How It Works

- The application splits the input text into words and processes them to create a concordance.
- Words are normalized to lowercase, and punctuation is removed for consistency.
- Each word's frequency and the sentences in which it appears are tracked.
- The result is an alphabetical listing of words with their corresponding frequencies and sentence numbers.

## Usage

1. Compile the `Concordance.java` file:
   ```bash
   javac Concordance.java

