class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int total = n + m - 1;
        char[] word = new char[total];
        boolean[] locked = new boolean[total];

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    int pos = i + j;
                    if (!locked[pos]) {
                        word[pos] = str2.charAt(j);
                        locked[pos] = true;
                    } else if (word[pos] != str2.charAt(j)) {
                        return "";
                    }
                }
            }
        }

        for (int i = 0; i < total; i++) {
            if (!locked[i]) {
                word[i] = 'a';
            }
        }

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                if (windowEqualsStr2(word, i, str2, m)) {
                    boolean fixed = false;
                    for (int j = m - 1; j >= 0; j--) {
                        int pos = i + j;
                        if (!locked[pos] && word[pos] < 'z') {
                            word[pos]++;
                            fixed = true;
                            break;
                        }
                    }
                    if (!fixed) return "";
                }
            }
        }

        return new String(word);
    }

    private boolean windowEqualsStr2(char[] word, int start, String str2, int m) {
        for (int j = 0; j < m; j++) {
            if (word[start + j] != str2.charAt(j)) return false;
        }
        return true;
    }
}