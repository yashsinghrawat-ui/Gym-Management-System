
import mysql.connector
import pandas as pd

con = mysql.connector.connect(
    host="localhost",
    user="root",
    password="password",
    database="gymdb"
)

query = "SELECT * FROM members"

df = pd.read_sql(query, con)

print("\nGym Member Report\n")
print(df)

df.to_csv("gym_report.csv", index=False)

print("\nReport Saved as gym_report.csv")
