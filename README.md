# Javadactyl
A Pterodactyl API wrapper for Java.

Currently unfinished! Check back later for updates.

Current progress: Preparing for Release 0.0.1 - https://trello.com/b/L0eps7oh/javadactyl

# Usage
To get started:
```Java
// Prints out the name and description of all servers owned by the cilent with the key.
Client client = new Client("ptlc_abcdefg", "https://ptero.example.org/");
for (ClientServer server : client.getServerList()) {
  System.out.println(server.getName());
  System.out.println(server.getDescription());
}
```
