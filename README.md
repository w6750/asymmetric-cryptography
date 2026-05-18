# Asymmetric Cryptography Practical

## Overview
This practical demonstrates RSA asymmetric cryptography using Java. It generates an RSA key pair, encrypts a plaintext message with the public key, and decrypts it with the private key.

## Theory
- **Asymmetric cryptography** uses two different keys: a public key and a private key.
- **RSA** is one of the most widely used asymmetric algorithms.
- **Public key** is used for encryption; **private key** is used for decryption.
- The public key can be shared openly, while the private key must remain secret.
- RSA relies on the mathematical difficulty of factoring the product of two large prime numbers.
- In this program, RSA key generation uses a 2048-bit key size.

## Key Concepts
- **Key pair generation**: `KeyPairGenerator.getInstance("RSA")`, `initialize(2048)`
- **Encryption**: `Cipher.getInstance("RSA")`, `ENCRYPT_MODE`
- **Decryption**: `Cipher.getInstance("RSA")`, `DECRYPT_MODE`
- **Base64 encoding**: used to display and transfer key bytes as text.
- **PKCS8EncodedKeySpec**: converts Base64-encoded private key text back into a `PrivateKey` object.

## Program Flow
1. Read plaintext message from user.
2. Generate RSA key pair.
3. Display public and private keys in Base64 format.
4. Encrypt plaintext with the public key.
5. Ask user to paste the private key for decryption.
6. Decrypt the encrypted bytes using the private key.
7. Display the decrypted plaintext.

## Exam Preparation
- Know differences between symmetric and asymmetric encryption.
- Understand why RSA uses different keys for encryption and decryption.
- Be able to describe the RSA workflow: key generation, encryption, decryption.
- Explain the role of Base64 in representing binary key data.
- Remember that secure practice never exposes private keys.

## Applications
- Secure email
- Digital signatures
- Secure key exchange
- TLS/SSL communication
