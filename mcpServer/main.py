from typing import Any
import psycopg2
from dotenv import load_dotenv
from mcp.server.fastmcp import FastMCP

load_dotenv()

mcp = FastMCP("mcpServer")

DB_HOST = 'localhost'
DB_PORT = 5432
DB_NAME = 'postgres'
DB_USER = 'jsjsjs'
DB_PASSWORD = 'jsjsjs'

def execute_query(query, params=None):
    'Executes a SQL query and returns the results.'
    conn = None
    try:
        conn = psycopg2.connect(
            host=DB_HOST,
            port=DB_PORT,
            database=DB_NAME,
            user=DB_USER,
            password=DB_PASSWORD,
        )
        cur = conn.cursor()
        cur.execute(query, params)
        column_names = [desc[0] for desc in cur.description]
        records = [dict(zip(column_names, row)) for row in cur.fetchall()]
        cur.close()
        return records
    except Exception as e:
        print(f"Error executing query: {e}")
        return []
    finally:
        if conn:
            conn.close()

@mcp.tool()
def get_lowest_pe_ttm(limit:int=10) -> str: 
    query = """
        SELECT * FROM Stock
        WHERE pettm IS NOT NULL
        ORDER BY pettm ASC
        LIMIT %s
        """
    records = execute_query(query, (limit,))
    return records

@mcp.tool()
def get_lowest_pb_quarterly(limit:int=10) -> str:
    query = """
        SELECT * FROM Stock
        WHERE pb_quarterly IS NOT NULL
        ORDER BY pb_quarterly ASC
        LIMIT %s
        """
    records = execute_query(query, (limit,))
    return records

@mcp.tool()
def get_highest_dividend_yield(limit:int=10) -> str:
    query = """
        SELECT * FROM Stock
        WHERE dividend_yield_indicated_annual IS NOT NULL
        ORDER BY dividend_yield_indicated_annual DESC
        LIMIT %s
        """
    records = execute_query(query, (limit,))
    return records

@mcp.tool()
def get_highest_roe_ttm(limit:int=10) -> str:
    query = """
        SELECT * FROM Stock
        WHERE roettm IS NOT NULL
        ORDER BY roettm DESC
        LIMIT %s
        """
    records = execute_query(query, (limit,))
    return records


if __name__ == "__main__":
    # Initialize and run the server
    mcp.run(transport='stdio')