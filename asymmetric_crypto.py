import base64
from cryptography.hazmat.primitives import hashes, serialization
from cryptography.hazmat.primitives.asymmetric import padding, rsa


def generate_key_pair():
    private_key = rsa.generate_private_key(public_exponent=65537, key_size=2048)
    public_key = private_key.public_key()

    public_bytes = public_key.public_bytes(
        encoding=serialization.Encoding.DER,
        format=serialization.PublicFormat.SubjectPublicKeyInfo,
    )

    private_bytes = private_key.private_bytes(
        encoding=serialization.Encoding.DER,
        format=serialization.PrivateFormat.PKCS8,
        encryption_algorithm=serialization.NoEncryption(),
    )

    public_key_b64 = base64.b64encode(public_bytes).decode("utf-8")
    private_key_b64 = base64.b64encode(private_bytes).decode("utf-8")

    return public_key, private_key, public_key_b64, private_key_b64


def encrypt_message(public_key, message: str) -> bytes:
    plaintext = message.encode("utf-8")
    return public_key.encrypt(
        plaintext,
        padding.PKCS1v15(),
    )


def load_private_key(private_key_b64: str):
    der = base64.b64decode(private_key_b64)
    return serialization.load_der_private_key(der, password=None)


def main():
    print("=================================")
    print("   RSA Asymmetric Cryptography")
    print("=================================\n")

    message = input("Enter message to encrypt: ")

    print("\nGenerating RSA Keys...")
    public_key, private_key, public_key_b64, private_key_b64 = generate_key_pair()
    print("Keys Generated Successfully!\n")

    print("---------- PUBLIC KEY ----------")
    print(public_key_b64)

    print("\n---------- PRIVATE KEY ----------")
    print(private_key_b64)

    encrypted_bytes = encrypt_message(public_key, message)
    encrypted_b64 = base64.b64encode(encrypted_bytes).decode("utf-8")

    print("\nEncrypted Message:")
    print(encrypted_b64)

    print("\nPaste the Private Key for Decryption:")
    entered_key = input().strip()

    loaded_private_key = load_private_key(entered_key)
    decrypted_bytes = loaded_private_key.decrypt(
        base64.b64decode(encrypted_b64),
        padding.PKCS1v15(),
    )
    decrypted_text = decrypted_bytes.decode("utf-8")

    print("\nDecrypted Message:")
    print(decrypted_text)


if __name__ == "__main__":
    main()
