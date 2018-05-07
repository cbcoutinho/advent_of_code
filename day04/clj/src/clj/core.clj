(ns clj.core)


(defn valid-passphrase
  "Checks if a passphrase is valid - only contains distinct words"
  [my-list]
  (apply distinct? my-list))
