package org.chance;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;

import org.chance.types.Month;

public abstract class ChanceData {

    public Collection<Integer> primes() {
        return Stream.of(
                // 1230 first primes, i.e. all primes up to the first one greater than 10000,
                // inclusive.
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101,
                103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211,
                223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337,
                347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461,
                463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601,
                607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739,
                743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881,
                883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019, 1021,
                1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129,
                1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223, 1229, 1231, 1237, 1249, 1259, 1277,
                1279, 1283, 1289, 1291, 1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381, 1399, 1409,
                1423, 1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499, 1511,
                1523, 1531, 1543, 1549, 1553, 1559, 1567, 1571, 1579, 1583, 1597, 1601, 1607, 1609, 1613, 1619, 1621,
                1627, 1637, 1657, 1663, 1667, 1669, 1693, 1697, 1699, 1709, 1721, 1723, 1733, 1741, 1747, 1753, 1759,
                1777, 1783, 1787, 1789, 1801, 1811, 1823, 1831, 1847, 1861, 1867, 1871, 1873, 1877, 1879, 1889, 1901,
                1907, 1913, 1931, 1933, 1949, 1951, 1973, 1979, 1987, 1993, 1997, 1999, 2003, 2011, 2017, 2027, 2029,
                2039, 2053, 2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111, 2113, 2129, 2131, 2137, 2141, 2143, 2153,
                2161, 2179, 2203, 2207, 2213, 2221, 2237, 2239, 2243, 2251, 2267, 2269, 2273, 2281, 2287, 2293, 2297,
                2309, 2311, 2333, 2339, 2341, 2347, 2351, 2357, 2371, 2377, 2381, 2383, 2389, 2393, 2399, 2411, 2417,
                2423, 2437, 2441, 2447, 2459, 2467, 2473, 2477, 2503, 2521, 2531, 2539, 2543, 2549, 2551, 2557, 2579,
                2591, 2593, 2609, 2617, 2621, 2633, 2647, 2657, 2659, 2663, 2671, 2677, 2683, 2687, 2689, 2693, 2699,
                2707, 2711, 2713, 2719, 2729, 2731, 2741, 2749, 2753, 2767, 2777, 2789, 2791, 2797, 2801, 2803, 2819,
                2833, 2837, 2843, 2851, 2857, 2861, 2879, 2887, 2897, 2903, 2909, 2917, 2927, 2939, 2953, 2957, 2963,
                2969, 2971, 2999, 3001, 3011, 3019, 3023, 3037, 3041, 3049, 3061, 3067, 3079, 3083, 3089, 3109, 3119,
                3121, 3137, 3163, 3167, 3169, 3181, 3187, 3191, 3203, 3209, 3217, 3221, 3229, 3251, 3253, 3257, 3259,
                3271, 3299, 3301, 3307, 3313, 3319, 3323, 3329, 3331, 3343, 3347, 3359, 3361, 3371, 3373, 3389, 3391,
                3407, 3413, 3433, 3449, 3457, 3461, 3463, 3467, 3469, 3491, 3499, 3511, 3517, 3527, 3529, 3533, 3539,
                3541, 3547, 3557, 3559, 3571, 3581, 3583, 3593, 3607, 3613, 3617, 3623, 3631, 3637, 3643, 3659, 3671,
                3673, 3677, 3691, 3697, 3701, 3709, 3719, 3727, 3733, 3739, 3761, 3767, 3769, 3779, 3793, 3797, 3803,
                3821, 3823, 3833, 3847, 3851, 3853, 3863, 3877, 3881, 3889, 3907, 3911, 3917, 3919, 3923, 3929, 3931,
                3943, 3947, 3967, 3989, 4001, 4003, 4007, 4013, 4019, 4021, 4027, 4049, 4051, 4057, 4073, 4079, 4091,
                4093, 4099, 4111, 4127, 4129, 4133, 4139, 4153, 4157, 4159, 4177, 4201, 4211, 4217, 4219, 4229, 4231,
                4241, 4243, 4253, 4259, 4261, 4271, 4273, 4283, 4289, 4297, 4327, 4337, 4339, 4349, 4357, 4363, 4373,
                4391, 4397, 4409, 4421, 4423, 4441, 4447, 4451, 4457, 4463, 4481, 4483, 4493, 4507, 4513, 4517, 4519,
                4523, 4547, 4549, 4561, 4567, 4583, 4591, 4597, 4603, 4621, 4637, 4639, 4643, 4649, 4651, 4657, 4663,
                4673, 4679, 4691, 4703, 4721, 4723, 4729, 4733, 4751, 4759, 4783, 4787, 4789, 4793, 4799, 4801, 4813,
                4817, 4831, 4861, 4871, 4877, 4889, 4903, 4909, 4919, 4931, 4933, 4937, 4943, 4951, 4957, 4967, 4969,
                4973, 4987, 4993, 4999, 5003, 5009, 5011, 5021, 5023, 5039, 5051, 5059, 5077, 5081, 5087, 5099, 5101,
                5107, 5113, 5119, 5147, 5153, 5167, 5171, 5179, 5189, 5197, 5209, 5227, 5231, 5233, 5237, 5261, 5273,
                5279, 5281, 5297, 5303, 5309, 5323, 5333, 5347, 5351, 5381, 5387, 5393, 5399, 5407, 5413, 5417, 5419,
                5431, 5437, 5441, 5443, 5449, 5471, 5477, 5479, 5483, 5501, 5503, 5507, 5519, 5521, 5527, 5531, 5557,
                5563, 5569, 5573, 5581, 5591, 5623, 5639, 5641, 5647, 5651, 5653, 5657, 5659, 5669, 5683, 5689, 5693,
                5701, 5711, 5717, 5737, 5741, 5743, 5749, 5779, 5783, 5791, 5801, 5807, 5813, 5821, 5827, 5839, 5843,
                5849, 5851, 5857, 5861, 5867, 5869, 5879, 5881, 5897, 5903, 5923, 5927, 5939, 5953, 5981, 5987, 6007,
                6011, 6029, 6037, 6043, 6047, 6053, 6067, 6073, 6079, 6089, 6091, 6101, 6113, 6121, 6131, 6133, 6143,
                6151, 6163, 6173, 6197, 6199, 6203, 6211, 6217, 6221, 6229, 6247, 6257, 6263, 6269, 6271, 6277, 6287,
                6299, 6301, 6311, 6317, 6323, 6329, 6337, 6343, 6353, 6359, 6361, 6367, 6373, 6379, 6389, 6397, 6421,
                6427, 6449, 6451, 6469, 6473, 6481, 6491, 6521, 6529, 6547, 6551, 6553, 6563, 6569, 6571, 6577, 6581,
                6599, 6607, 6619, 6637, 6653, 6659, 6661, 6673, 6679, 6689, 6691, 6701, 6703, 6709, 6719, 6733, 6737,
                6761, 6763, 6779, 6781, 6791, 6793, 6803, 6823, 6827, 6829, 6833, 6841, 6857, 6863, 6869, 6871, 6883,
                6899, 6907, 6911, 6917, 6947, 6949, 6959, 6961, 6967, 6971, 6977, 6983, 6991, 6997, 7001, 7013, 7019,
                7027, 7039, 7043, 7057, 7069, 7079, 7103, 7109, 7121, 7127, 7129, 7151, 7159, 7177, 7187, 7193, 7207,
                7211, 7213, 7219, 7229, 7237, 7243, 7247, 7253, 7283, 7297, 7307, 7309, 7321, 7331, 7333, 7349, 7351,
                7369, 7393, 7411, 7417, 7433, 7451, 7457, 7459, 7477, 7481, 7487, 7489, 7499, 7507, 7517, 7523, 7529,
                7537, 7541, 7547, 7549, 7559, 7561, 7573, 7577, 7583, 7589, 7591, 7603, 7607, 7621, 7639, 7643, 7649,
                7669, 7673, 7681, 7687, 7691, 7699, 7703, 7717, 7723, 7727, 7741, 7753, 7757, 7759, 7789, 7793, 7817,
                7823, 7829, 7841, 7853, 7867, 7873, 7877, 7879, 7883, 7901, 7907, 7919, 7927, 7933, 7937, 7949, 7951,
                7963, 7993, 8009, 8011, 8017, 8039, 8053, 8059, 8069, 8081, 8087, 8089, 8093, 8101, 8111, 8117, 8123,
                8147, 8161, 8167, 8171, 8179, 8191, 8209, 8219, 8221, 8231, 8233, 8237, 8243, 8263, 8269, 8273, 8287,
                8291, 8293, 8297, 8311, 8317, 8329, 8353, 8363, 8369, 8377, 8387, 8389, 8419, 8423, 8429, 8431, 8443,
                8447, 8461, 8467, 8501, 8513, 8521, 8527, 8537, 8539, 8543, 8563, 8573, 8581, 8597, 8599, 8609, 8623,
                8627, 8629, 8641, 8647, 8663, 8669, 8677, 8681, 8689, 8693, 8699, 8707, 8713, 8719, 8731, 8737, 8741,
                8747, 8753, 8761, 8779, 8783, 8803, 8807, 8819, 8821, 8831, 8837, 8839, 8849, 8861, 8863, 8867, 8887,
                8893, 8923, 8929, 8933, 8941, 8951, 8963, 8969, 8971, 8999, 9001, 9007, 9011, 9013, 9029, 9041, 9043,
                9049, 9059, 9067, 9091, 9103, 9109, 9127, 9133, 9137, 9151, 9157, 9161, 9173, 9181, 9187, 9199, 9203,
                9209, 9221, 9227, 9239, 9241, 9257, 9277, 9281, 9283, 9293, 9311, 9319, 9323, 9337, 9341, 9343, 9349,
                9371, 9377, 9391, 9397, 9403, 9413, 9419, 9421, 9431, 9433, 9437, 9439, 9461, 9463, 9467, 9473, 9479,
                9491, 9497, 9511, 9521, 9533, 9539, 9547, 9551, 9587, 9601, 9613, 9619, 9623, 9629, 9631, 9643, 9649,
                9661, 9677, 9679, 9689, 9697, 9719, 9721, 9733, 9739, 9743, 9749, 9767, 9769, 9781, 9787, 9791, 9803,
                9811, 9817, 9829, 9833, 9839, 9851, 9857, 9859, 9871, 9883, 9887, 9901, 9907, 9923, 9929, 9931, 9941,
                9949, 9967, 9973, 10007).collect(Collectors.toList());
    }

    public Collection<Month> months() {

        return Stream
                .of(new Month("January", "Jan", "01", 31), new Month("February", "Feb", "02", 28),
                        new Month("March", "Mar", "03", 31), new Month("April", "Apr", "04", 30),
                        new Month("May", "May", "05", 31), new Month("June", "Jun", "06", 30),
                        new Month("July", "Jul", "07", 31), new Month("August", "Aug", "08", 31),
                        new Month("September", "Sep", "09", 30), new Month("October", "Oct", "10", 31),
                        new Month("November", "Nov", "11", 30), new Month("December", "Dec", "12", 31))
                .collect(Collectors.toList());

    }

    public Map<String, Map<String, Collection<String>>> firstNames() {

        // Map<String,Object>
        Map<String, Map<String, Collection<String>>> firstNames = new HashMap<>();

        Map<String, Collection<String>> male = new HashMap<>();
        male.put("en", Arrays.asList("James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph",
                "Charles", "Thomas", "Christopher", "Daniel", "Matthew", "George", "Donald", "Anthony", "Paul", "Mark",
                "Edward", "Steven", "Kenneth", "Andrew", "Brian", "Joshua", "Kevin", "Ronald", "Timothy", "Jason",
                "Jeffrey", "Frank", "Gary", "Ryan", "Nicholas", "Eric", "Stephen", "Jacob", "Larry", "Jonathan",
                "Scott", "Raymond", "Justin", "Brandon", "Gregory", "Samuel", "Benjamin", "Patrick", "Jack", "Henry",
                "Walter", "Dennis", "Jerry", "Alexander", "Peter", "Tyler", "Douglas", "Harold", "Aaron", "Jose",
                "Adam", "Arthur", "Zachary", "Carl", "Nathan", "Albert", "Kyle", "Lawrence", "Joe", "Willie", "Gerald",
                "Roger", "Keith", "Jeremy", "Terry", "Harry", "Ralph", "Sean", "Jesse", "Roy", "Louis", "Billy",
                "Austin", "Bruce", "Eugene", "Christian", "Bryan", "Wayne", "Russell", "Howard", "Fred", "Ethan",
                "Jordan", "Philip", "Alan", "Juan", "Randy", "Vincent", "Bobby", "Dylan", "Johnny", "Phillip", "Victor",
                "Clarence", "Ernest", "Martin", "Craig", "Stanley", "Shawn", "Travis", "Bradley", "Leonard", "Earl",
                "Gabriel", "Jimmy", "Francis", "Todd", "Noah", "Danny", "Dale", "Cody", "Carlos", "Allen", "Frederick",
                "Logan", "Curtis", "Alex", "Joel", "Luis", "Norman", "Marvin", "Glenn", "Tony", "Nathaniel", "Rodney",
                "Melvin", "Alfred", "Steve", "Cameron", "Chad", "Edwin", "Caleb", "Evan", "Antonio", "Lee", "Herbert",
                "Jeffery", "Isaac", "Derek", "Ricky", "Marcus", "Theodore", "Elijah", "Luke", "Jesus", "Eddie", "Troy",
                "Mike", "Dustin", "Ray", "Adrian", "Bernard", "Leroy", "Angel", "Randall", "Wesley", "Ian", "Jared",
                "Mason", "Hunter", "Calvin", "Oscar", "Clifford", "Jay", "Shane", "Ronnie", "Barry", "Lucas", "Corey",
                "Manuel", "Leo", "Tommy", "Warren", "Jackson", "Isaiah", "Connor", "Don", "Dean", "Jon", "Julian",
                "Miguel", "Bill", "Lloyd", "Charlie", "Mitchell", "Leon", "Jerome", "Darrell", "Jeremiah", "Alvin",
                "Brett", "Seth", "Floyd", "Jim", "Blake", "Micheal", "Gordon", "Trevor", "Lewis", "Erik", "Edgar",
                "Vernon", "Devin", "Gavin", "Jayden", "Chris", "Clyde", "Tom", "Derrick", "Mario", "Brent", "Marc",
                "Herman", "Chase", "Dominic", "Ricardo", "Franklin", "Maurice", "Max", "Aiden", "Owen", "Lester",
                "Gilbert", "Elmer", "Gene", "Francisco", "Glen", "Cory", "Garrett", "Clayton", "Sam", "Jorge",
                "Chester", "Alejandro", "Jeff", "Harvey", "Milton", "Cole", "Ivan", "Andre", "Duane", "Landon"));
        male.put("it", Arrays.asList("Adolfo", "Alberto", "Aldo", "Alessandro", "Alessio", "Alfredo", "Alvaro",
                "Andrea", "Angelo", "Angiolo", "Antonino", "Antonio", "Attilio", "Benito", "Bernardo", "Bruno", "Carlo",
                "Cesare", "Christian", "Claudio", "Corrado", "Cosimo", "Cristian", "Cristiano", "Daniele", "Dario",
                "David", "Davide", "Diego", "Dino", "Domenico", "Duccio", "Edoardo", "Elia", "Elio", "Emanuele",
                "Emiliano", "Emilio", "Enrico", "Enzo", "Ettore", "Fabio", "Fabrizio", "Federico", "Ferdinando",
                "Fernando", "Filippo", "Francesco", "Franco", "Gabriele", "Giacomo", "Giampaolo", "Giampiero",
                "Giancarlo", "Gianfranco", "Gianluca", "Gianmarco", "Gianni", "Gino", "Giorgio", "Giovanni", "Giuliano",
                "Giulio", "Giuseppe", "Graziano", "Gregorio", "Guido", "Iacopo", "Jacopo", "Lapo", "Leonardo",
                "Lorenzo", "Luca", "Luciano", "Luigi", "Manuel", "Marcello", "Marco", "Marino", "Mario", "Massimiliano",
                "Massimo", "Matteo", "Mattia", "Maurizio", "Mauro", "Michele", "Mirko", "Mohamed", "Nello", "Neri",
                "Niccolò", "Nicola", "Osvaldo", "Otello", "Paolo", "Pier Luigi", "Piero", "Pietro", "Raffaele", "Remo",
                "Renato", "Renzo", "Riccardo", "Roberto", "Rolando", "Romano", "Salvatore", "Samuele", "Sandro",
                "Sergio", "Silvano", "Simone", "Stefano", "Thomas", "Tommaso", "Ubaldo", "Ugo", "Umberto", "Valerio",
                "Valter", "Vasco", "Vincenzo", "Vittorio"));
        male.put("nl", Arrays.asList("Aaron", "Abel", "Adam", "Adriaan", "Albert", "Alexander", "Ali", "Arjen", "Arno",
                "Bart", "Bas", "Bastiaan", "Benjamin", "Bob", "Boris", "Bram", "Brent", "Cas", "Casper", "Chris",
                "Christiaan", "Cornelis", "Daan", "Daley", "Damian", "Dani", "Daniel", "Daniël", "David", "Dean",
                "Dirk", "Dylan", "Egbert", "Elijah", "Erik", "Erwin", "Evert", "Ezra", "Fabian", "Fedde", "Finn",
                "Florian", "Floris", "Frank", "Frans", "Frederik", "Freek", "Geert", "Gerard", "Gerben", "Gerrit",
                "Gijs", "Guus", "Hans", "Hendrik", "Henk", "Herman", "Hidde", "Hugo", "Jaap", "Jan Jaap", "Jan-Willem",
                "Jack", "Jacob", "Jan", "Jason", "Jasper", "Jayden", "Jelle", "Jelte", "Jens", "Jeroen", "Jesse", "Jim",
                "Job", "Joep", "Johannes", "John", "Jonathan", "Joris", "Joshua", "Joël", "Julian", "Kees", "Kevin",
                "Koen", "Lars", "Laurens", "Leendert", "Lennard", "Lodewijk", "Luc", "Luca", "Lucas", "Lukas", "Luuk",
                "Maarten", "Marcus", "Martijn", "Martin", "Matthijs", "Maurits", "Max", "Mees", "Melle", "Mick", "Mika",
                "Milan", "Mohamed", "Mohammed", "Morris", "Muhammed", "Nathan", "Nick", "Nico", "Niek", "Niels", "Noah",
                "Noud", "Olivier", "Oscar", "Owen", "Paul", "Pepijn", "Peter", "Pieter", "Pim", "Quinten", "Reinier",
                "Rens", "Robin", "Ruben", "Sam", "Samuel", "Sander", "Sebastiaan", "Sem", "Sep", "Sepp", "Siem",
                "Simon", "Stan", "Stef", "Steven", "Stijn", "Sven", "Teun", "Thijmen", "Thijs", "Thomas", "Tijn", "Tim",
                "Timo", "Tobias", "Tom", "Victor", "Vince", "Willem", "Wim", "Wouter", "Yusuf"));
        male.put("fr", Arrays.asList("Aaron", "Abdon", "Abel", "Abélard", "Abelin", "Abondance", "Abraham", "Absalon",
                "Acace", "Achaire", "Achille", "Adalard", "Adalbald", "Adalbéron", "Adalbert", "Adalric", "Adam",
                "Adegrin", "Adel", "Adelin", "Andelin", "Adelphe", "Adam", "Adéodat", "Adhémar", "Adjutor", "Adolphe",
                "Adonis", "Adon", "Adrien", "Agapet", "Agathange", "Agathon", "Agilbert", "Agénor", "Agnan", "Aignan",
                "Agrippin", "Aimable", "Aimé", "Alain", "Alban", "Albin", "Aubin", "Albéric", "Albert", "Albertet",
                "Alcibiade", "Alcide", "Alcée", "Alcime", "Aldonce", "Aldric", "Aldéric", "Aleaume", "Alexandre",
                "Alexis", "Alix", "Alliaume", "Aleaume", "Almine", "Almire", "Aloïs", "Alphée", "Alphonse", "Alpinien",
                "Alverède", "Amalric", "Amaury", "Amandin", "Amant", "Ambroise", "Amédée", "Amélien", "Amiel", "Amour",
                "Anaël", "Anastase", "Anatole", "Ancelin", "Andéol", "Andoche", "André", "Andoche", "Ange", "Angelin",
                "Angilbe", "Anglebert", "Angoustan", "Anicet", "Anne", "Annibal", "Ansbert", "Anselme", "Anthelme",
                "Antheaume", "Anthime", "Antide", "Antoine", "Antonius", "Antonin", "Apollinaire", "Apollon", "Aquilin",
                "Arcade", "Archambaud", "Archambeau", "Archange", "Archibald", "Arian", "Ariel", "Ariste", "Aristide",
                "Armand", "Armel", "Armin", "Arnould", "Arnaud", "Arolde", "Arsène", "Arsinoé", "Arthaud", "Arthème",
                "Arthur", "Ascelin", "Athanase", "Aubry", "Audebert", "Audouin", "Audran", "Audric", "Auguste",
                "Augustin", "Aurèle", "Aurélien", "Aurian", "Auxence", "Axel", "Aymard", "Aymeric", "Aymon", "Aymond",
                "Balthazar", "Baptiste", "Barnabé", "Barthélemy", "Bartimée", "Basile", "Bastien", "Baudouin",
                "Bénigne", "Benjamin", "Benoît", "Bérenger", "Bérard", "Bernard", "Bertrand", "Blaise", "Bon",
                "Boniface", "Bouchard", "Brice", "Brieuc", "Bruno", "Brunon", "Calixte", "Calliste", "Camélien",
                "Camille", "Camillien", "Candide", "Caribert", "Carloman", "Cassandre", "Cassien", "Cédric", "Céleste",
                "Célestin", "Célien", "Césaire", "César", "Charles", "Charlemagne", "Childebert", "Chilpéric",
                "Chrétien", "Christian", "Christodule", "Christophe", "Chrysostome", "Clarence", "Claude", "Claudien",
                "Cléandre", "Clément", "Clotaire", "Côme", "Constance", "Constant", "Constantin", "Corentin", "Cyprien",
                "Cyriaque", "Cyrille", "Cyril", "Damien", "Daniel", "David", "Delphin", "Denis", "Désiré", "Didier",
                "Dieudonné", "Dimitri", "Dominique", "Dorian", "Dorothée", "Edgard", "Edmond", "Édouard", "Éleuthère",
                "Élie", "Élisée", "Émeric", "Émile", "Émilien", "Emmanuel", "Enguerrand", "Épiphane", "Éric", "Esprit",
                "Ernest", "Étienne", "Eubert", "Eudes", "Eudoxe", "Eugène", "Eusèbe", "Eustache", "Évariste", "Évrard",
                "Fabien", "Fabrice", "Falba", "Félicité", "Félix", "Ferdinand", "Fiacre", "Fidèle", "Firmin", "Flavien",
                "Flodoard", "Florent", "Florentin", "Florestan", "Florian", "Fortuné", "Foulques", "Francisque",
                "François", "Français", "Franciscus", "Francs", "Frédéric", "Fulbert", "Fulcran", "Fulgence", "Gabin",
                "Gabriel", "Gaël", "Garnier", "Gaston", "Gaspard", "Gatien", "Gaud", "Gautier", "Gédéon", "Geoffroy",
                "Georges", "Géraud", "Gérard", "Gerbert", "Germain", "Gervais", "Ghislain", "Gilbert", "Gilles",
                "Girart", "Gislebert", "Gondebaud", "Gonthier", "Gontran", "Gonzague", "Grégoire", "Guérin", "Gui",
                "Guillaume", "Gustave", "Guy", "Guyot", "Hardouin", "Hector", "Hédelin", "Hélier", "Henri", "Herbert",
                "Herluin", "Hervé", "Hilaire", "Hildebert", "Hincmar", "Hippolyte", "Honoré", "Hubert", "Hugues",
                "Innocent", "Isabeau", "Isidore", "Jacques", "Japhet", "Jason", "Jean", "Jeannel", "Jeannot", "Jérémie",
                "Jérôme", "Joachim", "Joanny", "Job", "Jocelyn", "Joël", "Johan", "Jonas", "Jonathan", "Joseph",
                "Josse", "Josselin", "Jourdain", "Jude", "Judicaël", "Jules", "Julien", "Juste", "Justin", "Lambert",
                "Landry", "Laurent", "Lazare", "Léandre", "Léon", "Léonard", "Léopold", "Leu", "Loup", "Leufroy",
                "Libère", "Liétald", "Lionel", "Loïc", "Longin", "Lorrain", "Lorraine", "Lothaire", "Louis", "Loup",
                "Luc", "Lucas", "Lucien", "Ludolphe", "Ludovic", "Macaire", "Malo", "Mamert", "Manassé", "Marc",
                "Marceau", "Marcel", "Marcelin", "Marius", "Marseille", "Martial", "Martin", "Mathurin", "Matthias",
                "Mathias", "Matthieu", "Maugis", "Maurice", "Mauricet", "Maxence", "Maxime", "Maximilien", "Mayeul",
                "Médéric", "Melchior", "Mence", "Merlin", "Mérovée", "Michaël", "Michel", "Moïse", "Morgan", "Nathan",
                "Nathanaël", "Narcisse", "Néhémie", "Nestor", "Nestor", "Nicéphore", "Nicolas", "Noé", "Noël",
                "Norbert", "Normand", "Normands", "Octave", "Odilon", "Odon", "Oger", "Olivier", "Oury", "Pacôme",
                "Palémon", "Parfait", "Pascal", "Paterne", "Patrice", "Paul", "Pépin", "Perceval", "Philémon",
                "Philibert", "Philippe", "Philothée", "Pie", "Pierre", "Pierrick", "Prosper", "Quentin", "Raoul",
                "Raphaël", "Raymond", "Régis", "Réjean", "Rémi", "Renaud", "René", "Reybaud", "Richard", "Robert",
                "Roch", "Rodolphe", "Rodrigue", "Roger", "Roland", "Romain", "Romuald", "Roméo", "Rome", "Ronan",
                "Roselin", "Salomon", "Samuel", "Savin", "Savinien", "Scholastique", "Sébastien", "Séraphin", "Serge",
                "Séverin", "Sidoine", "Sigebert", "Sigismond", "Silvère", "Simon", "Siméon", "Sixte", "Stanislas",
                "Stéphane", "Stephan", "Sylvain", "Sylvestre", "Tancrède", "Tanguy", "Taurin", "Théodore", "Théodose",
                "Théophile", "Théophraste", "Thibault", "Thibert", "Thierry", "Thomas", "Timoléon", "Timothée",
                "Titien", "Tonnin", "Toussaint", "Trajan", "Tristan", "Turold", "Tim", "Ulysse", "Urbain", "Valentin",
                "Valère", "Valéry", "Venance", "Venant", "Venceslas", "Vianney", "Victor", "Victorien", "Victorin",
                "Vigile", "Vincent", "Vital", "Vitalien", "Vivien", "Waleran", "Wandrille", "Xavier", "Xénophon",
                "Yves", "Zacharie", "Zaché", "Zéphirin"));

        Map<String, Collection<String>> female = new HashMap<>();
        female.put("en", Arrays.asList("Mary", "Emma", "Elizabeth", "Minnie", "Margaret", "Ida", "Alice", "Bertha",
                "Sarah", "Annie", "Clara", "Ella", "Florence", "Cora", "Martha", "Laura", "Nellie", "Grace", "Carrie",
                "Maude", "Mabel", "Bessie", "Jennie", "Gertrude", "Julia", "Hattie", "Edith", "Mattie", "Rose",
                "Catherine", "Lillian", "Ada", "Lillie", "Helen", "Jessie", "Louise", "Ethel", "Lula", "Myrtle", "Eva",
                "Frances", "Lena", "Lucy", "Edna", "Maggie", "Pearl", "Daisy", "Fannie", "Josephine", "Dora", "Rosa",
                "Katherine", "Agnes", "Marie", "Nora", "May", "Mamie", "Blanche", "Stella", "Ellen", "Nancy", "Effie",
                "Sallie", "Nettie", "Della", "Lizzie", "Flora", "Susie", "Maud", "Mae", "Etta", "Harriet", "Sadie",
                "Caroline", "Katie", "Lydia", "Elsie", "Kate", "Susan", "Mollie", "Alma", "Addie", "Georgia", "Eliza",
                "Lulu", "Nannie", "Lottie", "Amanda", "Belle", "Charlotte", "Rebecca", "Ruth", "Viola", "Olive",
                "Amelia", "Hannah", "Jane", "Virginia", "Emily", "Matilda", "Irene", "Kathryn", "Esther", "Willie",
                "Henrietta", "Ollie", "Amy", "Rachel", "Sara", "Estella", "Theresa", "Augusta", "Ora", "Pauline",
                "Josie", "Lola", "Sophia", "Leona", "Anne", "Mildred", "Ann", "Beulah", "Callie", "Lou", "Delia",
                "Eleanor", "Barbara", "Iva", "Louisa", "Maria", "Mayme", "Evelyn", "Estelle", "Nina", "Betty", "Marion",
                "Bettie", "Dorothy", "Luella", "Inez", "Lela", "Rosie", "Allie", "Millie", "Janie", "Cornelia",
                "Victoria", "Ruby", "Winifred", "Alta", "Celia", "Christine", "Beatrice", "Birdie", "Harriett", "Mable",
                "Myra", "Sophie", "Tillie", "Isabel", "Sylvia", "Carolyn", "Isabelle", "Leila", "Sally", "Ina", "Essie",
                "Bertie", "Nell", "Alberta", "Katharine", "Lora", "Rena", "Mina", "Rhoda", "Mathilda", "Abbie", "Eula",
                "Dollie", "Hettie", "Eunice", "Fanny", "Ola", "Lenora", "Adelaide", "Christina", "Lelia", "Nelle",
                "Sue", "Johanna", "Lilly", "Lucinda", "Minerva", "Lettie", "Roxie", "Cynthia", "Helena", "Hilda",
                "Hulda", "Bernice", "Genevieve", "Jean", "Cordelia", "Marian", "Francis", "Jeanette", "Adeline",
                "Gussie", "Leah", "Lois", "Lura", "Mittie", "Hallie", "Isabella", "Olga", "Phoebe", "Teresa", "Hester",
                "Lida", "Lina", "Winnie", "Claudia", "Marguerite", "Vera", "Cecelia", "Bess", "Emilie", "Rosetta",
                "Verna", "Myrtie", "Cecilia", "Elva", "Olivia", "Ophelia", "Georgie", "Elnora", "Violet", "Adele",
                "Lily", "Linnie", "Loretta", "Madge", "Polly", "Virgie", "Eugenia", "Lucile", "Lucille", "Mabelle",
                "Rosalie"));
        female.put("it", Arrays.asList("Ada", "Adriana", "Alessandra", "Alessia", "Alice", "Angela", "Anna",
                "Anna Maria", "Annalisa", "Annita", "Annunziata", "Antonella", "Arianna", "Asia", "Assunta", "Aurora",
                "Barbara", "Beatrice", "Benedetta", "Bianca", "Bruna", "Camilla", "Carla", "Carlotta", "Carmela",
                "Carolina", "Caterina", "Catia", "Cecilia", "Chiara", "Cinzia", "Clara", "Claudia", "Costanza",
                "Cristina", "Daniela", "Debora", "Diletta", "Dina", "Donatella", "Elena", "Eleonora", "Elisa",
                "Elisabetta", "Emanuela", "Emma", "Eva", "Federica", "Fernanda", "Fiorella", "Fiorenza", "Flora",
                "Franca", "Francesca", "Gabriella", "Gaia", "Gemma", "Giada", "Gianna", "Gina", "Ginevra", "Giorgia",
                "Giovanna", "Giulia", "Giuliana", "Giuseppa", "Giuseppina", "Grazia", "Graziella", "Greta", "Ida",
                "Ilaria", "Ines", "Iolanda", "Irene", "Irma", "Isabella", "Jessica", "Laura", "Lea", "Letizia", "Licia",
                "Lidia", "Liliana", "Lina", "Linda", "Lisa", "Livia", "Loretta", "Luana", "Lucia", "Luciana",
                "Lucrezia", "Luisa", "Manuela", "Mara", "Marcella", "Margherita", "Maria", "Maria Cristina",
                "Maria Grazia", "Maria Luisa", "Maria Pia", "Maria Teresa", "Marina", "Marisa", "Marta", "Martina",
                "Marzia", "Matilde", "Melissa", "Michela", "Milena", "Mirella", "Monica", "Natalina", "Nella",
                "Nicoletta", "Noemi", "Olga", "Paola", "Patrizia", "Piera", "Pierina", "Raffaella", "Rebecca", "Renata",
                "Rina", "Rita", "Roberta", "Rosa", "Rosanna", "Rossana", "Rossella", "Sabrina", "Sandra", "Sara",
                "Serena", "Silvana", "Silvia", "Simona", "Simonetta", "Sofia", "Sonia", "Stefania", "Susanna", "Teresa",
                "Tina", "Tiziana", "Tosca", "Valentina", "Valeria", "Vanda", "Vanessa", "Vanna", "Vera", "Veronica",
                "Vilma", "Viola", "Virginia", "Vittoria"));
        female.put("nl", Arrays.asList("Ada", "Arianne", "Afke", "Amanda", "Amber", "Amy", "Aniek", "Anita", "Anja",
                "Anna", "Anne", "Annelies", "Annemarie", "Annette", "Anouk", "Astrid", "Aukje", "Barbara", "Bianca",
                "Carla", "Carlijn", "Carolien", "Chantal", "Charlotte", "Claudia", "Daniëlle", "Debora", "Diane",
                "Dora", "Eline", "Elise", "Ella", "Ellen", "Emma", "Esmee", "Evelien", "Esther", "Erica", "Eva",
                "Femke", "Fleur", "Floor", "Froukje", "Gea", "Gerda", "Hanna", "Hanneke", "Heleen", "Hilde", "Ilona",
                "Ina", "Inge", "Ingrid", "Iris", "Isabel", "Isabelle", "Janneke", "Jasmijn", "Jeanine", "Jennifer",
                "Jessica", "Johanna", "Joke", "Julia", "Julie", "Karen", "Karin", "Katja", "Kim", "Lara", "Laura",
                "Lena", "Lianne", "Lieke", "Lilian", "Linda", "Lisa", "Lisanne", "Lotte", "Louise", "Maaike", "Manon",
                "Marga", "Maria", "Marissa", "Marit", "Marjolein", "Martine", "Marleen", "Melissa", "Merel", "Miranda",
                "Michelle", "Mirjam", "Mirthe", "Naomi", "Natalie", "Nienke", "Nina", "Noortje", "Olivia", "Patricia",
                "Paula", "Paulien", "Ramona", "Ria", "Rianne", "Roos", "Rosanne", "Ruth", "Sabrina", "Sandra", "Sanne",
                "Sara", "Saskia", "Silvia", "Sofia", "Sophie", "Sonja", "Suzanne", "Tamara", "Tess", "Tessa", "Tineke",
                "Valerie", "Vanessa", "Veerle", "Vera", "Victoria", "Wendy", "Willeke", "Yvonne", "Zoë"));
        female.put("fr", Arrays.asList("Abdon", "Abel", "Abigaëlle", "Abigaïl", "Acacius", "Acanthe", "Adalbert",
                "Adalsinde", "Adegrine", "Adélaïde", "Adèle", "Adélie", "Adeline", "Adeltrude", "Adolphe", "Adonis",
                "Adrastée", "Adrehilde", "Adrienne", "Agathe", "Agilbert", "Aglaé", "Aignan", "Agneflète", "Agnès",
                "Agrippine", "Aimé", "Alaine", "Alaïs", "Albane", "Albérade", "Alberte", "Alcide", "Alcine", "Alcyone",
                "Aldegonde", "Aleth", "Alexandrine", "Alexine", "Alice", "Aliénor", "Aliette", "Aline", "Alix", "Alizé",
                "Aloïse", "Aloyse", "Alphonsine", "Althée", "Amaliane", "Amalthée", "Amande", "Amandine", "Amant",
                "Amarande", "Amaranthe", "Amaryllis", "Ambre", "Ambroisie", "Amélie", "Améthyste", "Aminte", "Anaël",
                "Anaïs", "Anastasie", "Anatole", "Ancelin", "Andrée", "Anémone", "Angadrême", "Angèle", "Angeline",
                "Angélique", "Angilbert", "Anicet", "Annabelle", "Anne", "Annette", "Annick", "Annie", "Annonciade",
                "Ansbert", "Anstrudie", "Anthelme", "Antigone", "Antoinette", "Antonine", "Aphélie", "Apolline",
                "Apollonie", "Aquiline", "Arabelle", "Arcadie", "Archange", "Argine", "Ariane", "Aricie", "Ariel",
                "Arielle", "Arlette", "Armance", "Armande", "Armandine", "Armelle", "Armide", "Armelle", "Armin",
                "Arnaud", "Arsène", "Arsinoé", "Artémis", "Arthur", "Ascelin", "Ascension", "Assomption", "Astarté",
                "Astérie", "Astrée", "Astrid", "Athalie", "Athanasie", "Athina", "Aube", "Albert", "Aude", "Audrey",
                "Augustine", "Aure", "Aurélie", "Aurélien", "Aurèle", "Aurore", "Auxence", "Aveline", "Abigaëlle",
                "Avoye", "Axelle", "Aymard", "Azalée", "Adèle", "Adeline", "Barbe", "Basilisse", "Bathilde", "Béatrice",
                "Béatrix", "Bénédicte", "Bérengère", "Bernadette", "Berthe", "Bertille", "Beuve", "Blanche", "Blanc",
                "Blandine", "Brigitte", "Brune", "Brunehilde", "Callista", "Camille", "Capucine", "Carine", "Caroline",
                "Cassandre", "Catherine", "Cécile", "Céleste", "Célestine", "Céline", "Chantal", "Charlène", "Charline",
                "Charlotte", "Chloé", "Christelle", "Christiane", "Christine", "Claire", "Clara", "Claude", "Claudine",
                "Clarisse", "Clémence", "Clémentine", "Cléo", "Clio", "Clotilde", "Coline", "Conception", "Constance",
                "Coralie", "Coraline", "Corentine", "Corinne", "Cyrielle", "Daniel", "Daniel", "Daphné", "Débora",
                "Delphine", "Denise", "Diane", "Dieudonné", "Dominique", "Doriane", "Dorothée", "Douce", "Édith",
                "Edmée", "Éléonore", "Éliane", "Élia", "Éliette", "Élisabeth", "Élise", "Ella", "Élodie", "Éloïse",
                "Elsa", "Émeline", "Émérance", "Émérentienne", "Émérencie", "Émilie", "Emma", "Emmanuelle", "Emmelie",
                "Ernestine", "Esther", "Estelle", "Eudoxie", "Eugénie", "Eulalie", "Euphrasie", "Eusébie", "Évangéline",
                "Eva", "Ève", "Évelyne", "Fanny", "Fantine", "Faustine", "Félicie", "Fernande", "Flavie", "Fleur",
                "Flore", "Florence", "Florie", "Fortuné", "France", "Francia", "Françoise", "Francine", "Gabrielle",
                "Gaëlle", "Garance", "Geneviève", "Georgette", "Gerberge", "Germaine", "Gertrude", "Gisèle",
                "Guenièvre", "Guilhemine", "Guillemette", "Gustave", "Gwenael", "Hélène", "Héloïse", "Henriette",
                "Hermine", "Hermione", "Hippolyte", "Honorine", "Hortense", "Huguette", "Ines", "Irène", "Irina",
                "Iris", "Isabeau", "Isabelle", "Iseult", "Isolde", "Ismérie", "Jacinthe", "Jacqueline", "Jade",
                "Janine", "Jeanne", "Jocelyne", "Joëlle", "Joséphine", "Judith", "Julia", "Julie", "Jules", "Juliette",
                "Justine", "Katy", "Kathy", "Katie", "Laura", "Laure", "Laureline", "Laurence", "Laurene", "Lauriane",
                "Laurianne", "Laurine", "Léa", "Léna", "Léonie", "Léon", "Léontine", "Lorraine", "Lucie", "Lucienne",
                "Lucille", "Ludivine", "Lydie", "Lydie", "Megane", "Madeleine", "Magali", "Maguelone", "Mallaury",
                "Manon", "Marceline", "Margot", "Marguerite", "Marianne", "Marie", "Myriam", "Marie", "Marine",
                "Marion", "Marlène", "Marthe", "Martine", "Mathilde", "Maud", "Maureen", "Mauricette", "Maxime",
                "Mélanie", "Melissa", "Mélissandre", "Mélisande", "Mélodie", "Michel", "Micheline", "Mireille",
                "Miriam", "Moïse", "Monique", "Morgane", "Muriel", "Mylène", "Nadège", "Nadine", "Nathalie", "Nicole",
                "Nicolette", "Nine", "Noël", "Noémie", "Océane", "Odette", "Odile", "Olive", "Olivia", "Olympe",
                "Ombline", "Ombeline", "Ophélie", "Oriande", "Oriane", "Ozanne", "Pascale", "Pascaline", "Paule",
                "Paulette", "Pauline", "Priscille", "Prisca", "Prisque", "Pécine", "Pélagie", "Pénélope", "Perrine",
                "Pétronille", "Philippine", "Philomène", "Philothée", "Primerose", "Prudence", "Pulchérie", "Quentine",
                "Quiéta", "Quintia", "Quintilla", "Rachel", "Raphaëlle", "Raymonde", "Rebecca", "Régine", "Réjeanne",
                "René", "Rita", "Rita", "Rolande", "Romane", "Rosalie", "Rose", "Roseline", "Sabine", "Salomé",
                "Sandra", "Sandrine", "Sarah", "Ségolène", "Séverine", "Sibylle", "Simone", "Sixt", "Solange", "Soline",
                "Solène", "Sophie", "Stéphanie", "Suzanne", "Sylvain", "Sylvie", "Tatiana", "Thaïs", "Théodora",
                "Thérèse", "Tiphaine", "Ursule", "Valentine", "Valérie", "Véronique", "Victoire", "Victorine",
                "Vinciane", "Violette", "Virginie", "Viviane", "Xavière", "Yolande", "Ysaline", "Yvette", "Yvonne",
                "Zélie", "Zita", "Zoé"));

        firstNames.put("male", male);
        firstNames.put("female", male);

        return firstNames;
    }

    public Map<String, Collection<String>> lastNames() {
        Map<String, Collection<String>> lastNames = new HashMap<>();
        lastNames.put("en", Arrays.asList("Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson",
                "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia",
                "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young",
                "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez",
                "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans",
                "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell",
                "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson",
                "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood",
                "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes",
                "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell",
                "Griffin", "Diaz", "Hayes", "Myers", "Ford", "Hamilton", "Graham", "Sullivan", "Wallace", "Woods",
                "Cole", "West", "Jordan", "Owens", "Reynolds", "Fisher", "Ellis", "Harrison", "Gibson", "McDonald",
                "Cruz", "Marshall", "Ortiz", "Gomez", "Murray", "Freeman", "Wells", "Webb", "Simpson", "Stevens",
                "Tucker", "Porter", "Hunter", "Hicks", "Crawford", "Henry", "Boyd", "Mason", "Morales", "Kennedy",
                "Warren", "Dixon", "Ramos", "Reyes", "Burns", "Gordon", "Shaw", "Holmes", "Rice", "Robertson", "Hunt",
                "Black", "Daniels", "Palmer", "Mills", "Nichols", "Grant", "Knight", "Ferguson", "Rose", "Stone",
                "Hawkins", "Dunn", "Perkins", "Hudson", "Spencer", "Gardner", "Stephens", "Payne", "Pierce", "Berry",
                "Matthews", "Arnold", "Wagner", "Willis", "Ray", "Watkins", "Olson", "Carroll", "Duncan", "Snyder",
                "Hart", "Cunningham", "Bradley", "Lane", "Andrews", "Ruiz", "Harper", "Fox", "Riley", "Armstrong",
                "Carpenter", "Weaver", "Greene", "Lawrence", "Elliott", "Chavez", "Sims", "Austin", "Peters", "Kelley",
                "Franklin", "Lawson", "Fields", "Gutierrez", "Ryan", "Schmidt", "Carr", "Vasquez", "Castillo",
                "Wheeler", "Chapman", "Oliver", "Montgomery", "Richards", "Williamson", "Johnston", "Banks", "Meyer",
                "Bishop", "McCoy", "Howell", "Alvarez", "Morrison", "Hansen", "Fernandez", "Garza", "Harvey", "Little",
                "Burton", "Stanley", "Nguyen", "George", "Jacobs", "Reid", "Kim", "Fuller", "Lynch", "Dean", "Gilbert",
                "Garrett", "Romero", "Welch", "Larson", "Frazier", "Burke", "Hanson", "Day", "Mendoza", "Moreno",
                "Bowman", "Medina", "Fowler", "Brewer", "Hoffman", "Carlson", "Silva", "Pearson", "Holland", "Douglas",
                "Fleming", "Jensen", "Vargas", "Byrd", "Davidson", "Hopkins", "May", "Terry", "Herrera", "Wade", "Soto",
                "Walters", "Curtis", "Neal", "Caldwell", "Lowe", "Jennings", "Barnett", "Graves", "Jimenez", "Horton",
                "Shelton", "Barrett", "Obrien", "Castro", "Sutton", "Gregory", "McKinney", "Lucas", "Miles", "Craig",
                "Rodriquez", "Chambers", "Holt", "Lambert", "Fletcher", "Watts", "Bates", "Hale", "Rhodes", "Pena",
                "Beck", "Newman", "Haynes", "McDaniel", "Mendez", "Bush", "Vaughn", "Parks", "Dawson", "Santiago",
                "Norris", "Hardy", "Love", "Steele", "Curry", "Powers", "Schultz", "Barker", "Guzman", "Page", "Munoz",
                "Ball", "Keller", "Chandler", "Weber", "Leonard", "Walsh", "Lyons", "Ramsey", "Wolfe", "Schneider",
                "Mullins", "Benson", "Sharp", "Bowen", "Daniel", "Barber", "Cummings", "Hines", "Baldwin", "Griffith",
                "Valdez", "Hubbard", "Salazar", "Reeves", "Warner", "Stevenson", "Burgess", "Santos", "Tate", "Cross",
                "Garner", "Mann", "Mack", "Moss", "Thornton", "Dennis", "McGee", "Farmer", "Delgado", "Aguilar", "Vega",
                "Glover", "Manning", "Cohen", "Harmon", "Rodgers", "Robbins", "Newton", "Todd", "Blair", "Higgins",
                "Ingram", "Reese", "Cannon", "Strickland", "Townsend", "Potter", "Goodwin", "Walton", "Rowe", "Hampton",
                "Ortega", "Patton", "Swanson", "Joseph", "Francis", "Goodman", "Maldonado", "Yates", "Becker",
                "Erickson", "Hodges", "Rios", "Conner", "Adkins", "Webster", "Norman", "Malone", "Hammond", "Flowers",
                "Cobb", "Moody", "Quinn", "Blake", "Maxwell", "Pope", "Floyd", "Osborne", "Paul", "McCarthy",
                "Guerrero", "Lindsey", "Estrada", "Sandoval", "Gibbs", "Tyler", "Gross", "Fitzgerald", "Stokes",
                "Doyle", "Sherman", "Saunders", "Wise", "Colon", "Gill", "Alvarado", "Greer", "Padilla", "Simon",
                "Waters", "Nunez", "Ballard", "Schwartz", "McBride", "Houston", "Christensen", "Klein", "Pratt",
                "Briggs", "Parsons", "McLaughlin", "Zimmerman", "French", "Buchanan", "Moran", "Copeland", "Roy",
                "Pittman", "Brady", "McCormick", "Holloway", "Brock", "Poole", "Frank", "Logan", "Owen", "Bass",
                "Marsh", "Drake", "Wong", "Jefferson", "Park", "Morton", "Abbott", "Sparks", "Patrick", "Norton",
                "Huff", "Clayton", "Massey", "Lloyd", "Figueroa", "Carson", "Bowers", "Roberson", "Barton", "Tran",
                "Lamb", "Harrington", "Casey", "Boone", "Cortez", "Clarke", "Mathis", "Singleton", "Wilkins", "Cain",
                "Bryan", "Underwood", "Hogan", "McKenzie", "Collier", "Luna", "Phelps", "McGuire", "Allison", "Bridges",
                "Wilkerson", "Nash", "Summers", "Atkins"));
        lastNames.put("it", Arrays.asList("Acciai", "Aglietti", "Agostini", "Agresti", "Ahmed", "Aiazzi", "Albanese",
                "Alberti", "Alessi", "Alfani", "Alinari", "Alterini", "Amato", "Ammannati", "Ancillotti", "Andrei",
                "Andreini", "Andreoni", "Angeli", "Anichini", "Antonelli", "Antonini", "Arena", "Ariani", "Arnetoli",
                "Arrighi", "Baccani", "Baccetti", "Bacci", "Bacherini", "Badii", "Baggiani", "Baglioni", "Bagni",
                "Bagnoli", "Baldassini", "Baldi", "Baldini", "Ballerini", "Balli", "Ballini", "Balloni", "Bambi",
                "Banchi", "Bandinelli", "Bandini", "Bani", "Barbetti", "Barbieri", "Barchielli", "Bardazzi", "Bardelli",
                "Bardi", "Barducci", "Bargellini", "Bargiacchi", "Barni", "Baroncelli", "Baroncini", "Barone", "Baroni",
                "Baronti", "Bartalesi", "Bartoletti", "Bartoli", "Bartolini", "Bartoloni", "Bartolozzi", "Basagni",
                "Basile", "Bassi", "Batacchi", "Battaglia", "Battaglini", "Bausi", "Becagli", "Becattini", "Becchi",
                "Becucci", "Bellandi", "Bellesi", "Belli", "Bellini", "Bellucci", "Bencini", "Benedetti", "Benelli",
                "Beni", "Benini", "Bensi", "Benucci", "Benvenuti", "Berlincioni", "Bernacchioni", "Bernardi",
                "Bernardini", "Berni", "Bernini", "Bertelli", "Berti", "Bertini", "Bessi", "Betti", "Bettini", "Biagi",
                "Biagini", "Biagioni", "Biagiotti", "Biancalani", "Bianchi", "Bianchini", "Bianco", "Biffoli",
                "Bigazzi", "Bigi", "Biliotti", "Billi", "Binazzi", "Bindi", "Bini", "Biondi", "Bizzarri", "Bocci",
                "Bogani", "Bolognesi", "Bonaiuti", "Bonanni", "Bonciani", "Boncinelli", "Bondi", "Bonechi", "Bongini",
                "Boni", "Bonini", "Borchi", "Boretti", "Borghi", "Borghini", "Borgioli", "Borri", "Borselli", "Boschi",
                "Bottai", "Bracci", "Braccini", "Brandi", "Braschi", "Bravi", "Brazzini", "Breschi", "Brilli", "Brizzi",
                "Brogelli", "Brogi", "Brogioni", "Brunelli", "Brunetti", "Bruni", "Bruno", "Brunori", "Bruschi",
                "Bucci", "Bucciarelli", "Buccioni", "Bucelli", "Bulli", "Burberi", "Burchi", "Burgassi", "Burroni",
                "Bussotti", "Buti", "Caciolli", "Caiani", "Calabrese", "Calamai", "Calamandrei", "Caldini", "Calo",
                "Calonaci", "Calosi", "Calvelli", "Cambi", "Camiciottoli", "Cammelli", "Cammilli", "Campolmi",
                "Cantini", "Capanni", "Capecchi", "Caponi", "Cappelletti", "Cappelli", "Cappellini", "Cappugi",
                "Capretti", "Caputo", "Carbone", "Carboni", "Cardini", "Carlesi", "Carletti", "Carli", "Caroti",
                "Carotti", "Carrai", "Carraresi", "Carta", "Caruso", "Casalini", "Casati", "Caselli", "Casini",
                "Castagnoli", "Castellani", "Castelli", "Castellucci", "Catalano", "Catarzi", "Catelani", "Cavaciocchi",
                "Cavallaro", "Cavallini", "Cavicchi", "Cavini", "Ceccarelli", "Ceccatelli", "Ceccherelli", "Ceccherini",
                "Cecchi", "Cecchini", "Cecconi", "Cei", "Cellai", "Celli", "Cellini", "Cencetti", "Ceni", "Cenni",
                "Cerbai", "Cesari", "Ceseri", "Checcacci", "Checchi", "Checcucci", "Cheli", "Chellini", "Chen", "Cheng",
                "Cherici", "Cherubini", "Chiaramonti", "Chiarantini", "Chiarelli", "Chiari", "Chiarini", "Chiarugi",
                "Chiavacci", "Chiesi", "Chimenti", "Chini", "Chirici", "Chiti", "Ciabatti", "Ciampi", "Cianchi",
                "Cianfanelli", "Cianferoni", "Ciani", "Ciapetti", "Ciappi", "Ciardi", "Ciatti", "Cicali", "Ciccone",
                "Cinelli", "Cini", "Ciobanu", "Ciolli", "Cioni", "Cipriani", "Cirillo", "Cirri", "Ciucchi", "Ciuffi",
                "Ciulli", "Ciullini", "Clemente", "Cocchi", "Cognome", "Coli", "Collini", "Colombo", "Colzi",
                "Comparini", "Conforti", "Consigli", "Conte", "Conti", "Contini", "Coppini", "Coppola", "Corsi",
                "Corsini", "Corti", "Cortini", "Cosi", "Costa", "Costantini", "Costantino", "Cozzi", "Cresci",
                "Crescioli", "Cresti", "Crini", "Curradi", "D'Agostino", "D'Alessandro", "D'Amico", "D'Angelo", "Daddi",
                "Dainelli", "Dallai", "Danti", "Davitti", "De Angelis", "De Luca", "De Marco", "De Rosa", "De Santis",
                "De Simone", "De Vita", "Degl'Innocenti", "Degli Innocenti", "Dei", "Del Lungo", "Del Re", "Di Marco",
                "Di Stefano", "Dini", "Diop", "Dobre", "Dolfi", "Donati", "Dondoli", "Dong", "Donnini", "Ducci",
                "Dumitru", "Ermini", "Esposito", "Evangelisti", "Fabbri", "Fabbrini", "Fabbrizzi", "Fabbroni",
                "Fabbrucci", "Fabiani", "Facchini", "Faggi", "Fagioli", "Failli", "Faini", "Falciani", "Falcini",
                "Falcone", "Fallani", "Falorni", "Falsini", "Falugiani", "Fancelli", "Fanelli", "Fanetti", "Fanfani",
                "Fani", "Fantappie", "Fantechi", "Fanti", "Fantini", "Fantoni", "Farina", "Fattori", "Favilli", "Fedi",
                "Fei", "Ferrante", "Ferrara", "Ferrari", "Ferraro", "Ferretti", "Ferri", "Ferrini", "Ferroni",
                "Fiaschi", "Fibbi", "Fiesoli", "Filippi", "Filippini", "Fini", "Fioravanti", "Fiore", "Fiorentini",
                "Fiorini", "Fissi", "Focardi", "Foggi", "Fontana", "Fontanelli", "Fontani", "Forconi", "Formigli",
                "Forte", "Forti", "Fortini", "Fossati", "Fossi", "Francalanci", "Franceschi", "Franceschini", "Franchi",
                "Franchini", "Franci", "Francini", "Francioni", "Franco", "Frassineti", "Frati", "Fratini", "Frilli",
                "Frizzi", "Frosali", "Frosini", "Frullini", "Fusco", "Fusi", "Gabbrielli", "Gabellini", "Gagliardi",
                "Galanti", "Galardi", "Galeotti", "Galletti", "Galli", "Gallo", "Gallori", "Gambacciani", "Gargani",
                "Garofalo", "Garuglieri", "Gashi", "Gasperini", "Gatti", "Gelli", "Gensini", "Gentile", "Gentili",
                "Geri", "Gerini", "Gheri", "Ghini", "Giachetti", "Giachi", "Giacomelli", "Gianassi", "Giani",
                "Giannelli", "Giannetti", "Gianni", "Giannini", "Giannoni", "Giannotti", "Giannozzi", "Gigli",
                "Giordano", "Giorgetti", "Giorgi", "Giovacchini", "Giovannelli", "Giovannetti", "Giovannini",
                "Giovannoni", "Giuliani", "Giunti", "Giuntini", "Giusti", "Gonnelli", "Goretti", "Gori", "Gradi",
                "Gramigni", "Grassi", "Grasso", "Graziani", "Grazzini", "Greco", "Grifoni", "Grillo", "Grimaldi",
                "Grossi", "Gualtieri", "Guarducci", "Guarino", "Guarnieri", "Guasti", "Guerra", "Guerri", "Guerrini",
                "Guidi", "Guidotti", "He", "Hoxha", "Hu", "Huang", "Iandelli", "Ignesti", "Innocenti", "Jin", "La Rosa",
                "Lai", "Landi", "Landini", "Lanini", "Lapi", "Lapini", "Lari", "Lascialfari", "Lastrucci", "Latini",
                "Lazzeri", "Lazzerini", "Lelli", "Lenzi", "Leonardi", "Leoncini", "Leone", "Leoni", "Lepri", "Li",
                "Liao", "Lin", "Linari", "Lippi", "Lisi", "Livi", "Lombardi", "Lombardini", "Lombardo", "Longo",
                "Lopez", "Lorenzi", "Lorenzini", "Lorini", "Lotti", "Lu", "Lucchesi", "Lucherini", "Lunghi", "Lupi",
                "Madiai", "Maestrini", "Maffei", "Maggi", "Maggini", "Magherini", "Magini", "Magnani", "Magnelli",
                "Magni", "Magnolfi", "Magrini", "Malavolti", "Malevolti", "Manca", "Mancini", "Manetti", "Manfredi",
                "Mangani", "Mannelli", "Manni", "Mannini", "Mannucci", "Manuelli", "Manzini", "Marcelli", "Marchese",
                "Marchetti", "Marchi", "Marchiani", "Marchionni", "Marconi", "Marcucci", "Margheri", "Mari", "Mariani",
                "Marilli", "Marinai", "Marinari", "Marinelli", "Marini", "Marino", "Mariotti", "Marsili", "Martelli",
                "Martinelli", "Martini", "Martino", "Marzi", "Masi", "Masini", "Masoni", "Massai", "Materassi",
                "Mattei", "Matteini", "Matteucci", "Matteuzzi", "Mattioli", "Mattolini", "Matucci", "Mauro", "Mazzanti",
                "Mazzei", "Mazzetti", "Mazzi", "Mazzini", "Mazzocchi", "Mazzoli", "Mazzoni", "Mazzuoli", "Meacci",
                "Mecocci", "Meini", "Melani", "Mele", "Meli", "Mengoni", "Menichetti", "Meoni", "Merlini", "Messeri",
                "Messina", "Meucci", "Miccinesi", "Miceli", "Micheli", "Michelini", "Michelozzi", "Migliori",
                "Migliorini", "Milani", "Miniati", "Misuri", "Monaco", "Montagnani", "Montagni", "Montanari",
                "Montelatici", "Monti", "Montigiani", "Montini", "Morandi", "Morandini", "Morelli", "Moretti",
                "Morganti", "Mori", "Morini", "Moroni", "Morozzi", "Mugnai", "Mugnaini", "Mustafa", "Naldi", "Naldini",
                "Nannelli", "Nanni", "Nannini", "Nannucci", "Nardi", "Nardini", "Nardoni", "Natali", "Ndiaye",
                "Nencetti", "Nencini", "Nencioni", "Neri", "Nesi", "Nesti", "Niccolai", "Niccoli", "Niccolini", "Nigi",
                "Nistri", "Nocentini", "Noferini", "Novelli", "Nucci", "Nuti", "Nutini", "Oliva", "Olivieri", "Olmi",
                "Orlandi", "Orlandini", "Orlando", "Orsini", "Ortolani", "Ottanelli", "Pacciani", "Pace", "Paci",
                "Pacini", "Pagani", "Pagano", "Paggetti", "Pagliai", "Pagni", "Pagnini", "Paladini", "Palagi",
                "Palchetti", "Palloni", "Palmieri", "Palumbo", "Pampaloni", "Pancani", "Pandolfi", "Pandolfini",
                "Panerai", "Panichi", "Paoletti", "Paoli", "Paolini", "Papi", "Papini", "Papucci", "Parenti", "Parigi",
                "Parisi", "Parri", "Parrini", "Pasquini", "Passeri", "Pecchioli", "Pecorini", "Pellegrini", "Pepi",
                "Perini", "Perrone", "Peruzzi", "Pesci", "Pestelli", "Petri", "Petrini", "Petrucci", "Pettini",
                "Pezzati", "Pezzatini", "Piani", "Piazza", "Piazzesi", "Piazzini", "Piccardi", "Picchi", "Piccini",
                "Piccioli", "Pieraccini", "Pieraccioni", "Pieralli", "Pierattini", "Pieri", "Pierini", "Pieroni",
                "Pietrini", "Pini", "Pinna", "Pinto", "Pinzani", "Pinzauti", "Piras", "Pisani", "Pistolesi", "Poggesi",
                "Poggi", "Poggiali", "Poggiolini", "Poli", "Pollastri", "Porciani", "Pozzi", "Pratellesi", "Pratesi",
                "Prosperi", "Pruneti", "Pucci", "Puccini", "Puccioni", "Pugi", "Pugliese", "Puliti", "Querci",
                "Quercioli", "Raddi", "Radu", "Raffaelli", "Ragazzini", "Ranfagni", "Ranieri", "Rastrelli", "Raugei",
                "Raveggi", "Renai", "Renzi", "Rettori", "Ricci", "Ricciardi", "Ridi", "Ridolfi", "Rigacci", "Righi",
                "Righini", "Rinaldi", "Risaliti", "Ristori", "Rizzo", "Rocchi", "Rocchini", "Rogai", "Romagnoli",
                "Romanelli", "Romani", "Romano", "Romei", "Romeo", "Romiti", "Romoli", "Romolini", "Rontini", "Rosati",
                "Roselli", "Rosi", "Rossetti", "Rossi", "Rossini", "Rovai", "Ruggeri", "Ruggiero", "Russo", "Sabatini",
                "Saccardi", "Sacchetti", "Sacchi", "Sacco", "Salerno", "Salimbeni", "Salucci", "Salvadori",
                "Salvestrini", "Salvi", "Salvini", "Sanesi", "Sani", "Sanna", "Santi", "Santini", "Santoni", "Santoro",
                "Santucci", "Sardi", "Sarri", "Sarti", "Sassi", "Sbolci", "Scali", "Scarpelli", "Scarselli",
                "Scopetani", "Secci", "Selvi", "Senatori", "Senesi", "Serafini", "Sereni", "Serra", "Sestini",
                "Sguanci", "Sieni", "Signorini", "Silvestri", "Simoncini", "Simonetti", "Simoni", "Singh", "Sodi",
                "Soldi", "Somigli", "Sorbi", "Sorelli", "Sorrentino", "Sottili", "Spina", "Spinelli", "Staccioli", "",
                "Stefanelli", "Stefani", "Stefanini", "Stella", "Susini", "Tacchi", "Tacconi", "Taddei", "Tagliaferri",
                "Tamburini", "Tanganelli", "Tani", "Tanini", "Tapinassi", "Tarchi", "Tarchiani", "Targioni", "Tassi",
                "Tassini", "Tempesti", "Terzani", "Tesi", "Testa", "Testi", "Tilli", "Tinti", "Tirinnanzi",
                "Toccafondi", "Tofanari", "Tofani", "Tognaccini", "Tonelli", "Tonini", "Torelli", "Torrini", "Tosi",
                "Toti", "Tozzi", "Trambusti", "Trapani", "Tucci", "Turchi", "Ugolini", "Ulivi", "Valente", "Valenti",
                "Valentini", "Vangelisti", "Vanni", "Vannini", "Vannoni", "Vannozzi", "Vannucchi", "Vannucci",
                "Ventura", "Venturi", "Venturini", "Vestri", "Vettori", "Vichi", "Viciani", "Vieri", "Vigiani",
                "Vignoli", "Vignolini", "Vignozzi", "Villani", "Vinci", "Visani", "Vitale", "Vitali", "Viti", "Viviani",
                "Vivoli", "Volpe", "Volpi", "Wang", "Wu", "Xu", "Yang", "Ye", "Zagli", "Zani", "Zanieri", "Zanobini",
                "Zecchi", "Zetti", "Zhang", "Zheng", "Zhou", "Zhu", "Zingoni", "Zini", "Zoppi"));
        lastNames.put("nl", Arrays.asList("Albers", "Alblas", "Appelman", "Baars", "Baas", "Bakker", "Blank", "Bleeker",
                "Blok", "Blom", "Boer", "Boers", "Boldewijn", "Boon", "Boot", "Bos", "Bosch", "Bosma", "Bosman",
                "Bouma", "Bouman", "Bouwman", "Brands", "Brouwer", "Burger", "Buijs", "Buitenhuis", "Ceder", "Cohen",
                "Dekker", "Dekkers", "Dijkman", "Dijkstra", "Driessen", "Drost", "Engel", "Evers", "Faber", "Franke",
                "Gerritsen", "Goedhart", "Goossens", "Groen", "Groenenberg", "Groot", "Haan", "Hart", "Heemskerk",
                "Hendriks", "Hermans", "Hoekstra", "Hofman", "Hopman", "Huisman", "Jacobs", "Jansen", "Janssen",
                "Jonker", "Jaspers", "Keijzer", "Klaassen", "Klein", "Koek", "Koenders", "Kok", "Kool", "Koopman",
                "Koopmans", "Koning", "Koster", "Kramer", "Kroon", "Kuijpers", "Kuiper", "Kuipers", "Kurt", "Koster",
                "Kwakman", "Los", "Lubbers", "Maas", "Markus", "Martens", "Meijer", "Mol", "Molenaar", "Mulder",
                "Nieuwenhuis", "Peeters", "Peters", "Pengel", "Pieters", "Pool", "Post", "Postma", "Prins", "Pronk",
                "Reijnders", "Rietveld", "Roest", "Roos", "Sanders", "Schaap", "Scheffer", "Schenk", "Schilder",
                "Schipper", "Schmidt", "Scholten", "Schouten", "Schut", "Schutte", "Schuurman", "Simons", "Smeets",
                "Smit", "Smits", "Snel", "Swinkels", "Tas", "Terpstra", "Timmermans", "Tol", "Tromp", "Troost", "Valk",
                "Veenstra", "Veldkamp", "Verbeek", "Verheul", "Verhoeven", "Vermeer", "Vermeulen", "Verweij", "Vink",
                "Visser", "Voorn", "Vos", "Wagenaar", "Wiersema", "Willems", "Willemsen", "Witteveen", "Wolff",
                "Wolters", "Zijlstra", "Zwart", "de Beer", "de Boer", "de Bruijn", "de Bruin", "de Graaf", "de Groot",
                "de Haan", "de Haas", "de Jager", "de Jong", "de Jonge", "de Koning", "de Lange", "de Leeuw",
                "de Ridder", "de Rooij", "de Ruiter", "de Vos", "de Vries", "de Waal", "de Wit", "de Zwart", "van Beek",
                "van Boven", "van Dam", "van Dijk", "van Dongen", "van Doorn", "van Egmond", "van Eijk", "van Es",
                "van Gelder", "van Gelderen", "van Houten", "van Hulst", "van Kempen", "van Kesteren", "van Leeuwen",
                "van Loon", "van Mill", "van Noord", "van Ommen", "van Ommeren", "van Oosten", "van Oostveen",
                "van Rijn", "van Schaik", "van Veen", "van Vliet", "van Wijk", "van Wijngaarden", "van den Poel",
                "van de Pol", "van den Ploeg", "van de Ven", "van den Berg", "van den Bosch", "van den Brink",
                "van den Broek", "van den Heuvel", "van der Heijden", "van der Horst", "van der Hulst", "van der Kroon",
                "van der Laan", "van der Linden", "van der Meer", "van der Meij", "van der Meulen", "van der Molen",
                "van der Sluis", "van der Spek", "van der Veen", "van der Velde", "van der Velden", "van der Vliet",
                "van der Wal"));
        lastNames.put("uk", Arrays.asList("Smith", "Jones", "Williams", "Taylor", "Brown", "Davies", "Evans", "Wilson",
                "Thomas", "Johnson", "Roberts", "Robinson", "Thompson", "Wright", "Walker", "White", "Edwards",
                "Hughes", "Green", "Hall", "Lewis", "Harris", "Clarke", "Patel", "Jackson", "Wood", "Turner", "Martin",
                "Cooper", "Hill", "Ward", "Morris", "Moore", "Clark", "Lee", "King", "Baker", "Harrison", "Morgan",
                "Allen", "James", "Scott", "Phillips", "Watson", "Davis", "Parker", "Price", "Bennett", "Young",
                "Griffiths", "Mitchell", "Kelly", "Cook", "Carter", "Richardson", "Bailey", "Collins", "Bell", "Shaw",
                "Murphy", "Miller", "Cox", "Richards", "Khan", "Marshall", "Anderson", "Simpson", "Ellis", "Adams",
                "Singh", "Begum", "Wilkinson", "Foster", "Chapman", "Powell", "Webb", "Rogers", "Gray", "Mason", "Ali",
                "Hunt", "Hussain", "Campbell", "Matthews", "Owen", "Palmer", "Holmes", "Mills", "Barnes", "Knight",
                "Lloyd", "Butler", "Russell", "Barker", "Fisher", "Stevens", "Jenkins", "Murray", "Dixon", "Harvey",
                "Graham", "Pearson", "Ahmed", "Fletcher", "Walsh", "Kaur", "Gibson", "Howard", "Andrews", "Stewart",
                "Elliott", "Reynolds", "Saunders", "Payne", "Fox", "Ford", "Pearce", "Day", "Brooks", "West",
                "Lawrence", "Cole", "Atkinson", "Bradley", "Spencer", "Gill", "Dawson", "Ball", "Burton", "O'brien",
                "Watts", "Rose", "Booth", "Perry", "Ryan", "Grant", "Wells", "Armstrong", "Francis", "Rees", "Hayes",
                "Hart", "Hudson", "Newman", "Barrett", "Webster", "Hunter", "Gregory", "Carr", "Lowe", "Page", "Marsh",
                "Riley", "Dunn", "Woods", "Parsons", "Berry", "Stone", "Reid", "Holland", "Hawkins", "Harding",
                "Porter", "Robertson", "Newton", "Oliver", "Reed", "Kennedy", "Williamson", "Bird", "Gardner", "Shah",
                "Dean", "Lane", "Cooke", "Bates", "Henderson", "Parry", "Burgess", "Bishop", "Walton", "Burns",
                "Nicholson", "Shepherd", "Ross", "Cross", "Long", "Freeman", "Warren", "Nicholls", "Hamilton", "Byrne",
                "Sutton", "Mcdonald", "Yates", "Hodgson", "Robson", "Curtis", "Hopkins", "O'connor", "Harper",
                "Coleman", "Watkins", "Moss", "Mccarthy", "Chambers", "O'neill", "Griffin", "Sharp", "Hardy", "Wheeler",
                "Potter", "Osborne", "Johnston", "Gordon", "Doyle", "Wallace", "George", "Jordan", "Hutchinson", "Rowe",
                "Burke", "May", "Pritchard", "Gilbert", "Willis", "Higgins", "Read", "Miles", "Stevenson", "Stephenson",
                "Hammond", "Arnold", "Buckley", "Walters", "Hewitt", "Barber", "Nelson", "Slater", "Austin", "Sullivan",
                "Whitehead", "Mann", "Frost", "Lambert", "Stephens", "Blake", "Akhtar", "Lynch", "Goodwin", "Barton",
                "Woodward", "Thomson", "Cunningham", "Quinn", "Barnett", "Baxter", "Bibi", "Clayton", "Nash",
                "Greenwood", "Jennings", "Holt", "Kemp", "Poole", "Gallagher", "Bond", "Stokes", "Tucker", "Davidson",
                "Fowler", "Heath", "Norman", "Middleton", "Lawson", "Banks", "French", "Stanley", "Jarvis", "Gibbs",
                "Ferguson", "Hayward", "Carroll", "Douglas", "Dickinson", "Todd", "Barlow", "Peters", "Lucas",
                "Knowles", "Hartley", "Miah", "Simmons", "Morton", "Alexander", "Field", "Morrison", "Norris",
                "Townsend", "Preston", "Hancock", "Thornton", "Baldwin", "Burrows", "Briggs", "Parkinson", "Reeves",
                "Macdonald", "Lamb", "Black", "Abbott", "Sanders", "Thorpe", "Holden", "Tomlinson", "Perkins", "Ashton",
                "Rhodes", "Fuller", "Howe", "Bryant", "Vaughan", "Dale", "Davey", "Weston", "Bartlett", "Whittaker",
                "Davison", "Kent", "Skinner", "Birch", "Morley", "Daniels", "Glover", "Howell", "Cartwright", "Pugh",
                "Humphreys", "Goddard", "Brennan", "Wall", "Kirby", "Bowen", "Savage", "Bull", "Wong", "Dobson",
                "Smart", "Wilkins", "Kirk", "Fraser", "Duffy", "Hicks", "Patterson", "Bradshaw", "Little", "Archer",
                "Warner", "Waters", "'sullivan", "Farrell", "Brookes", "Atkins", "Kay", "Dodd", "Bentley", "Flynn",
                "John", "Schofield", "Short", "Haynes", "Wade", "Butcher", "Henry", "Sanderson", "Crawford", "Sheppard",
                "Bolton", "Coates", "Giles", "Gould", "Houghton", "Gibbons", "Pratt", "Manning", "Law", "Hooper",
                "Noble", "Dyer", "Rahman", "Clements", "Moran", "Sykes", "Chan", "Doherty", "Connolly", "Joyce",
                "Franklin", "Hobbs", "Coles", "Herbert", "Steele", "Kerr", "Leach", "Winter", "Owens", "Duncan",
                "Naylor", "Fleming", "Horton", "Finch", "Fitzgerald", "Randall", "Carpenter", "Marsden", "Browne",
                "Garner", "Pickering", "Hale", "Dennis", "Vincent", "Chadwick", "Chandler", "Sharpe", "Nolan", "Lyons",
                "Hurst", "Collier", "Peacock", "Howarth", "Faulkner", "Rice", "Pollard", "Welch", "Norton", "Gough",
                "Sinclair", "Blackburn", "Bryan", "Conway", "Power", "Cameron", "Daly", "Allan", "Hanson", "Gardiner",
                "Boyle", "Myers", "Turnbull", "Wallis", "Mahmood", "Sims", "Swift", "Iqbal", "Pope", "Brady",
                "Chamberlain", "Rowley", "Tyler", "Farmer", "Metcalfe", "Hilton", "Godfrey", "Holloway", "Parkin",
                "Bray", "Talbot", "Donnelly", "Nixon", "Charlton", "Benson", "Whitehouse", "Barry", "Hope", "Lord",
                "North", "Storey", "Connor", "Potts", "Bevan", "Hargreaves", "Mclean", "Mistry", "Bruce", "Howells",
                "Hyde", "Parkes", "Wyatt", "Fry", "Lees", "'donnell", "Craig", "Forster", "Mckenzie", "Humphries",
                "Mellor", "Carey", "Ingram", "Summers", "Leonard"));
        lastNames.put("de",
                Arrays.asList("Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Meyer", "Wagner", "Becker",
                        "Schulz", "Hoffmann", "Schäfer", "Koch", "Bauer", "Richter", "Klein", "Wolf", "Schröder",
                        "Neumann", "Schwarz", "Zimmermann", "Braun", "Krüger", "Hofmann", "Hartmann", "Lange",
                        "Schmitt", "Werner", "Schmitz", "Krause", "Meier", "Lehmann", "Schmid", "Schulze", "Maier",
                        "Köhler", "Herrmann", "König", "Walter", "Mayer", "Huber", "Kaiser", "Fuchs", "Peters", "Lang",
                        "Scholz", "Möller", "Weiß", "Jung", "Hahn", "Schubert", "Vogel", "Friedrich", "Keller",
                        "Günther", "Frank", "Berger", "Winkler", "Roth", "Beck", "Lorenz", "Baumann", "Franke",
                        "Albrecht", "Schuster", "Simon", "Ludwig", "Böhm", "Winter", "Kraus", "Martin", "Schumacher",
                        "Krämer", "Vogt", "Stein", "Jäger", "Otto", "Sommer", "Groß", "Seidel", "Heinrich", "Brandt",
                        "Haas", "Schreiber", "Graf", "Schulte", "Dietrich", "Ziegler", "Kuhn", "Kühn", "Pohl", "Engel",
                        "Horn", "Busch", "Bergmann", "Thomas", "Voigt", "Sauer", "Arnold", "Wolff", "Pfeiffer"));
        lastNames.put("jp", Arrays.asList("Sato", "Suzuki", "Takahashi", "Tanaka", "Watanabe", "Ito", "Yamamoto",
                "Nakamura", "Kobayashi", "Kato", "Yoshida", "Yamada", "Sasaki", "Yamaguchi", "Saito", "Matsumoto",
                "Inoue", "Kimura", "Hayashi", "Shimizu", "Yamazaki", "Mori", "Abe", "Ikeda", "Hashimoto", "Yamashita",
                "Ishikawa", "Nakajima", "Maeda", "Fujita", "Ogawa", "Goto", "Okada", "Hasegawa", "Murakami", "Kondo",
                "Ishii", "Saito", "Sakamoto", "Endo", "Aoki", "Fujii", "Nishimura", "Fukuda", "Ota", "Miura",
                "Fujiwara", "Okamoto", "Matsuda", "Nakagawa", "Nakano", "Harada", "Ono", "Tamura", "Takeuchi", "Kaneko",
                "Wada", "Nakayama", "Ishida", "Ueda", "Morita", "Hara", "Shibata", "Sakai", "Kudo", "Yokoyama",
                "Miyazaki", "Miyamoto", "Uchida", "Takagi", "Ando", "Taniguchi", "Ohno", "Maruyama", "Imai", "Takada",
                "Fujimoto", "Takeda", "Murata", "Ueno", "Sugiyama", "Masuda", "Sugawara", "Hirano", "Kojima", "Otsuka",
                "Chiba", "Kubo", "Matsui", "Iwasaki", "Sakurai", "Kinoshita", "Noguchi", "Matsuo", "Nomura", "Kikuchi",
                "Sano", "Onishi", "Sugimoto", "Arai"));
        lastNames.put("es",
                Arrays.asList("Garcia", "Fernandez", "Lopez", "Martinez", "Gonzalez", "Rodriguez", "Sanchez", "Perez",
                        "Martin", "Gomez", "Ruiz", "Diaz", "Hernandez", "Alvarez", "Jimenez", "Moreno", "Munoz",
                        "Alonso", "Romero", "Navarro", "Gutierrez", "Torres", "Dominguez", "Gil", "Vazquez", "Blanco",
                        "Serrano", "Ramos", "Castro", "Suarez", "Sanz", "Rubio", "Ortega", "Molina", "Delgado", "Ortiz",
                        "Morales", "Ramirez", "Marin", "Iglesias", "Santos", "Castillo", "Garrido", "Calvo", "Pena",
                        "Cruz", "Cano", "Nunez", "Prieto", "Diez", "Lozano", "Vidal", "Pascual", "Ferrer", "Medina",
                        "Vega", "Leon", "Herrero", "Vicente", "Mendez", "Guerrero", "Fuentes", "Campos", "Nieto",
                        "Cortes", "Caballero", "Ibanez", "Lorenzo", "Pastor", "Gimenez", "Saez", "Soler", "Marquez",
                        "Carrasco", "Herrera", "Montero", "Arias", "Crespo", "Flores", "Andres", "Aguilar", "Hidalgo",
                        "Cabrera", "Mora", "Duran", "Velasco", "Rey", "Pardo", "Roman", "Vila", "Bravo", "Merino",
                        "Moya", "Soto", "Izquierdo", "Reyes", "Redondo", "Marcos", "Carmona", "Menendez"));
        lastNames.put("fr", Arrays.asList("Martin", "Bernard", "Thomas", "Petit", "Robert", "Richard", "Durand",
                "Dubois", "Moreau", "Laurent", "Simon", "Michel", "Lefèvre", "Leroy", "Roux", "David", "Bertrand",
                "Morel", "Fournier", "Girard", "Bonnet", "Dupont", "Lambert", "Fontaine", "Rousseau", "Vincent",
                "Müller", "Lefèvre", "Faure", "André", "Mercier", "Blanc", "Guérin", "Boyer", "Garnier", "Chevalier",
                "François", "Legrand", "Gauthier", "Garcia", "Perrin", "Robin", "Clément", "Morin", "Nicolas", "Henry",
                "Roussel", "Matthieu", "Gautier", "Masson", "Marchand", "Duval", "Denis", "Dumont", "Marie", "Lemaire",
                "Noël", "Meyer", "Dufour", "Meunier", "Brun", "Blanchard", "Giraud", "Joly", "Rivière", "Lucas",
                "Brunet", "Gaillard", "Barbier", "Arnaud", "Martínez", "Gérard", "Roche", "Renard", "Schmitt", "Roy",
                "Leroux", "Colin", "Vidal", "Caron", "Picard", "Roger", "Fabre", "Aubert", "Lemoine", "Renaud", "Dumas",
                "Lacroix", "Olivier", "Philippe", "Bourgeois", "Pierre", "Benoît", "Rey", "Leclerc", "Payet", "Rolland",
                "Leclercq", "Guillaume", "Lecomte", "López", "Jean", "Dupuy", "Guillot", "Hubert", "Berger",
                "Carpentier", "Sánchez", "Dupuis", "Moulin", "Louis", "Deschamps", "Huet", "Vasseur", "Perez",
                "Boucher", "Fleury", "Royer", "Klein", "Jacquet", "Adam", "Paris", "Poirier", "Marty", "Aubry", "Guyot",
                "Carré", "Charles", "Renault", "Charpentier", "Ménard", "Maillard", "Baron", "Bertin", "Bailly",
                "Hervé", "Schneider", "Fernández", "Le GallGall", "Collet", "Léger", "Bouvier", "Julien", "Prévost",
                "Millet", "Perrot", "Daniel", "Le RouxRoux", "Cousin", "Germain", "Breton", "Besson", "Langlois",
                "Rémi", "Le GoffGoff", "Pelletier", "Lévêque", "Perrier", "Leblanc", "Barré", "Lebrun", "Marchal",
                "Weber", "Mallet", "Hamon", "Boulanger", "Jacob", "Monnier", "Michaud", "Rodríguez", "Guichard",
                "Gillet", "Étienne", "Grondin", "Poulain", "Tessier", "Chevallier", "Collin", "Chauvin",
                "Da SilvaSilva", "Bouchet", "Gay", "Lemaître", "Bénard", "Maréchal", "Humbert", "Reynaud", "Antoine",
                "Hoarau", "Perret", "Barthélemy", "Cordier", "Pichon", "Lejeune", "Gilbert", "Lamy", "Delaunay",
                "Pasquier", "Carlier", "LaporteLaporte"));

        return lastNames;
    }

    public Map<String, Collection<Map<String, String>>> streetSuffixes() {
        Gson gson = new Gson();
        return gson.fromJson("{" + "    \"us\": [" + "    {\"name\": \"Avenue\", \"abbreviation\": \"Ave\"},"
                + "    {\"name\": \"Boulevard\", \"abbreviation\": \"Blvd\"},"
                + "    {\"name\": \"Center\", \"abbreviation\": \"Ctr\"},"
                + "    {\"name\": \"Circle\", \"abbreviation\": \"Cir\"},"
                + "    {\"name\": \"Court\", \"abbreviation\": \"Ct\"},"
                + "    {\"name\": \"Drive\", \"abbreviation\": \"Dr\"},"
                + "    {\"name\": \"Extension\", \"abbreviation\": \"Ext\"},"
                + "    {\"name\": \"Glen\", \"abbreviation\": \"Gln\"},"
                + "    {\"name\": \"Grove\", \"abbreviation\": \"Grv\"},"
                + "    {\"name\": \"Heights\", \"abbreviation\": \"Hts\"},"
                + "    {\"name\": \"Highway\", \"abbreviation\": \"Hwy\"},"
                + "    {\"name\": \"Junction\", \"abbreviation\": \"Jct\"},"
                + "    {\"name\": \"Key\", \"abbreviation\": \"Key\"},"
                + "    {\"name\": \"Lane\", \"abbreviation\": \"Ln\"},"
                + "    {\"name\": \"Loop\", \"abbreviation\": \"Loop\"},"
                + "    {\"name\": \"Manor\", \"abbreviation\": \"Mnr\"},"
                + "    {\"name\": \"Mill\", \"abbreviation\": \"Mill\"},"
                + "    {\"name\": \"Park\", \"abbreviation\": \"Park\"},"
                + "    {\"name\": \"Parkway\", \"abbreviation\": \"Pkwy\"},"
                + "    {\"name\": \"Pass\", \"abbreviation\": \"Pass\"},"
                + "    {\"name\": \"Path\", \"abbreviation\": \"Path\"},"
                + "    {\"name\": \"Pike\", \"abbreviation\": \"Pike\"},"
                + "    {\"name\": \"Place\", \"abbreviation\": \"Pl\"},"
                + "    {\"name\": \"Plaza\", \"abbreviation\": \"Plz\"},"
                + "    {\"name\": \"Point\", \"abbreviation\": \"Pt\"},"
                + "    {\"name\": \"Ridge\", \"abbreviation\": \"Rdg\"},"
                + "    {\"name\": \"River\", \"abbreviation\": \"Riv\"},"
                + "    {\"name\": \"Road\", \"abbreviation\": \"Rd\"},"
                + "    {\"name\": \"Square\", \"abbreviation\": \"Sq\"},"
                + "    {\"name\": \"Street\", \"abbreviation\": \"St\"},"
                + "    {\"name\": \"Terrace\", \"abbreviation\": \"Ter\"},"
                + "    {\"name\": \"Trail\", \"abbreviation\": \"Trl\"},"
                + "    {\"name\": \"Turnpike\", \"abbreviation\": \"Tpke\"},"
                + "    {\"name\": \"View\", \"abbreviation\": \"Vw\"},"
                + "    {\"name\": \"Way\", \"abbreviation\": \"Way\"}" + "]," + "\"it\": ["
                + "    { \"name\": \"Accesso\", \"abbreviation\": \"Acc.\" },"
                + "    { \"name\": \"Alzaia\", \"abbreviation\": \"Alz.\" },"
                + "    { \"name\": \"Arco\", \"abbreviation\": \"Arco\" },"
                + "    { \"name\": \"Archivolto\", \"abbreviation\": \"Acv.\" },"
                + "    { \"name\": \"Arena\", \"abbreviation\": \"Arena\" },"
                + "    { \"name\": \"Argine\", \"abbreviation\": \"Argine\" },"
                + "    { \"name\": \"Bacino\", \"abbreviation\": \"Bacino\" },"
                + "    { \"name\": \"Banchi\", \"abbreviation\": \"Banchi\" },"
                + "    { \"name\": \"Banchina\", \"abbreviation\": \"Ban.\" },"
                + "    { \"name\": \"Bastioni\", \"abbreviation\": \"Bas.\" },"
                + "    { \"name\": \"Belvedere\", \"abbreviation\": \"Belv.\" },"
                + "    { \"name\": \"Borgata\", \"abbreviation\": \"B.ta\" },"
                + "    { \"name\": \"Borgo\", \"abbreviation\": \"B.go\" },"
                + "    { \"name\": \"Calata\", \"abbreviation\": \"Cal.\" },"
                + "    { \"name\": \"Calle\", \"abbreviation\": \"Calle\" },"
                + "    { \"name\": \"Campiello\", \"abbreviation\": \"Cam.\" },"
                + "    { \"name\": \"Campo\", \"abbreviation\": \"Cam.\" },"
                + "    { \"name\": \"Canale\", \"abbreviation\": \"Can.\" },"
                + "    { \"name\": \"Carraia\", \"abbreviation\": \"Carr.\" },"
                + "    { \"name\": \"Cascina\", \"abbreviation\": \"Cascina\" },"
                + "    { \"name\": \"Case sparse\", \"abbreviation\": \"c.s.\" },"
                + "    { \"name\": \"Cavalcavia\", \"abbreviation\": \"Cv.\" },"
                + "    { \"name\": \"Circonvallazione\", \"abbreviation\": \"Cv.\" },"
                + "    { \"name\": \"Complanare\", \"abbreviation\": \"C.re\" },"
                + "    { \"name\": \"Contrada\", \"abbreviation\": \"C.da\" },"
                + "    { \"name\": \"Corso\", \"abbreviation\": \"C.so\" },"
                + "    { \"name\": \"Corte\", \"abbreviation\": \"C.te\" },"
                + "    { \"name\": \"Cortile\", \"abbreviation\": \"C.le\" },"
                + "    { \"name\": \"Diramazione\", \"abbreviation\": \"Dir.\" },"
                + "    { \"name\": \"Fondaco\", \"abbreviation\": \"F.co\" },"
                + "    { \"name\": \"Fondamenta\", \"abbreviation\": \"F.ta\" },"
                + "    { \"name\": \"Fondo\", \"abbreviation\": \"F.do\" },"
                + "    { \"name\": \"Frazione\", \"abbreviation\": \"Fr.\" },"
                + "    { \"name\": \"Isola\", \"abbreviation\": \"Is.\" },"
                + "    { \"name\": \"Largo\", \"abbreviation\": \"L.go\" },"
                + "    { \"name\": \"Litoranea\", \"abbreviation\": \"Lit.\" },"
                + "    { \"name\": \"Lungolago\", \"abbreviation\": \"L.go lago\" },"
                + "    { \"name\": \"Lungo Po\", \"abbreviation\": \"l.go Po\" },"
                + "    { \"name\": \"Molo\", \"abbreviation\": \"Molo\" },"
                + "    { \"name\": \"Mura\", \"abbreviation\": \"Mura\" },"
                + "    { \"name\": \"Passaggio privato\", \"abbreviation\": \"pass. priv.\" },"
                + "    { \"name\": \"Passeggiata\", \"abbreviation\": \"Pass.\" },"
                + "    { \"name\": \"Piazza\", \"abbreviation\": \"P.zza\" },"
                + "    { \"name\": \"Piazzale\", \"abbreviation\": \"P.le\" },"
                + "    { \"name\": \"Ponte\", \"abbreviation\": \"P.te\" },"
                + "    { \"name\": \"Portico\", \"abbreviation\": \"P.co\" },"
                + "    { \"name\": \"Rampa\", \"abbreviation\": \"Rampa\" },"
                + "    { \"name\": \"Regione\", \"abbreviation\": \"Reg.\" },"
                + "    { \"name\": \"Rione\", \"abbreviation\": \"R.ne\" },"
                + "    { \"name\": \"Rio\", \"abbreviation\": \"Rio\" },"
                + "    { \"name\": \"Ripa\", \"abbreviation\": \"Ripa\" },"
                + "    { \"name\": \"Riva\", \"abbreviation\": \"Riva\" },"
                + "    { \"name\": \"Rondò\", \"abbreviation\": \"Rondò\" },"
                + "    { \"name\": \"Rotonda\", \"abbreviation\": \"Rot.\" },"
                + "    { \"name\": \"Sagrato\", \"abbreviation\": \"Sagr.\" },"
                + "    { \"name\": \"Salita\", \"abbreviation\": \"Sal.\" },"
                + "    { \"name\": \"Scalinata\", \"abbreviation\": \"Scal.\" },"
                + "    { \"name\": \"Scalone\", \"abbreviation\": \"Scal.\" },"
                + "    { \"name\": \"Slargo\", \"abbreviation\": \"Sl.\" },"
                + "    { \"name\": \"Sottoportico\", \"abbreviation\": \"Sott.\" },"
                + "    { \"name\": \"Strada\", \"abbreviation\": \"Str.\" },"
                + "    { \"name\": \"Stradale\", \"abbreviation\": \"Str.le\" },"
                + "    { \"name\": \"Strettoia\", \"abbreviation\": \"Strett.\" },"
                + "    { \"name\": \"Traversa\", \"abbreviation\": \"Trav.\" },"
                + "    { \"name\": \"Via\", \"abbreviation\": \"V.\" },"
                + "    { \"name\": \"Viale\", \"abbreviation\": \"V.le\" },"
                + "    { \"name\": \"Vicinale\", \"abbreviation\": \"Vic.le\" },"
                + "    { \"name\": \"Vicolo\", \"abbreviation\": \"Vic.\" }" + "]," + "\"uk\" : ["
                + "    {\"name\": \"Avenue\", \"abbreviation\": \"Ave\"},"
                + "    {\"name\": \"Close\", \"abbreviation\": \"Cl\"},"
                + "    {\"name\": \"Court\", \"abbreviation\": \"Ct\"},"
                + "    {\"name\": \"Crescent\", \"abbreviation\": \"Cr\"},"
                + "    {\"name\": \"Drive\", \"abbreviation\": \"Dr\"},"
                + "    {\"name\": \"Garden\", \"abbreviation\": \"Gdn\"},"
                + "    {\"name\": \"Gardens\", \"abbreviation\": \"Gdns\"},"
                + "    {\"name\": \"Green\", \"abbreviation\": \"Gn\"},"
                + "    {\"name\": \"Grove\", \"abbreviation\": \"Gr\"},"
                + "    {\"name\": \"Lane\", \"abbreviation\": \"Ln\"},"
                + "    {\"name\": \"Mount\", \"abbreviation\": \"Mt\"},"
                + "    {\"name\": \"Place\", \"abbreviation\": \"Pl\"},"
                + "    {\"name\": \"Park\", \"abbreviation\": \"Pk\"},"
                + "    {\"name\": \"Ridge\", \"abbreviation\": \"Rdg\"},"
                + "    {\"name\": \"Road\", \"abbreviation\": \"Rd\"},"
                + "    {\"name\": \"Square\", \"abbreviation\": \"Sq\"},"
                + "    {\"name\": \"Street\", \"abbreviation\": \"St\"},"
                + "    {\"name\": \"Terrace\", \"abbreviation\": \"Ter\"},"
                + "    {\"name\": \"Valley\", \"abbreviation\": \"Val\"}" + "]" + "}", Map.class);
    }
}