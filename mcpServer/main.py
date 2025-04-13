from typing import Any
import psycopg2
from dotenv import load_dotenv
from mcp.server.fastmcp import FastMCP
import requests

load_dotenv()

mcp = FastMCP("mcpServer")

DB_HOST = 'localhost'
DB_PORT = 5432
DB_NAME = 'postgres'
DB_USER = 'jsjsjs'
DB_PASSWORD = 'jsjsjs'
ALPHA_KEY = '' # fill this field

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
def get_lowest_pe_ttm(limit:int=80) -> str:
    query = """
        SELECT * FROM Stock
        WHERE pettm IS NOT NULL AND pettm != 0.0
        ORDER BY pettm ASC
        LIMIT %s
        """
    records = execute_query(query, (limit,))
    return records

@mcp.tool()
def get_lowest_pb_quarterly(limit:int=50) -> str:
    query = """
        SELECT * FROM Stock
        WHERE pb_quarterly IS NOT NULL and pb_quarterly != 0.0
        ORDER BY pb_quarterly ASC
        LIMIT %s
        """
    records = execute_query(query, (limit,))
    return records

@mcp.tool()
def get_highest_dividend_yield(limit:int=50) -> str:
    query = """
        SELECT * FROM Stock
        WHERE dividend_yield_indicated_annual IS NOT NULL and dividend_yield_indicated_annual != 0.0
        ORDER BY dividend_yield_indicated_annual DESC
        LIMIT %s
        """
    records = execute_query(query, (limit,))
    return records

@mcp.tool()
def get_highest_roe_ttm(limit:int=50) -> str:
    query = """
        SELECT * FROM Stock
        WHERE roettm IS NOT NULL and roettm !=0.0
        ORDER BY roettm DESC
        LIMIT %s
        """
    records = execute_query(query, (limit,))
    return records


@mcp.tool()
def get_market_news(limit:int=50) -> str:
    """
    returns live and historical market news & sentiment data from a large & growing selection of premier news outlets around the world
    """
    # replace the "demo" apikey below with your own key from https://www.alphavantage.co/support/#api-key
    url = 'https://www.alphavantage.co/query?function=NEWS_SENTIMENT&sort=LATEST&limit=' + str(limit) + '&apikey=' + ALPHA_KEY
    response = requests.get(url)
    news = response.json()

    extracted_data = []
    for item in news["feed"]:
        extracted_data.append({
            "title": item["title"],
            "time_published": item["time_published"],
            "summary": item["summary"]
        })
    return str(extracted_data)

@mcp.tool()
def get_us_treasury_yield_monthly(limit:int=10) -> str:
    """
    This API returns the daily, weekly, and monthly US treasury yield of a given maturity timeline (e.g., 5 year, 30 year, etc).
    """
    url = 'https://www.alphavantage.co/query?function=TREASURY_YIELD&interval=monthly&maturity=10year&apikey=' + ALPHA_KEY
    response = requests.get(url)
    news = response.json()

    extracted_data = []
    for i, item in enumerate(news["data"]):
        if i >= limit:
            break
        extracted_data.append({
            "date": item["date"],
            "value": item["value"]
        })
    return str(extracted_data)

@mcp.tool()
def get_us_federal_funds_rate_monthly(limit:int=10) -> str:
    """
    This API returns the daily, weekly, and monthly federal funds rate (interest rate) of the United States.
    """
    url = 'https://www.alphavantage.co/query?function=FEDERAL_FUNDS_RATE&apikey=' + ALPHA_KEY
    response = requests.get(url)
    news = response.json()

    extracted_data = []
    for i, item in enumerate(news["data"]):
        if i >= limit:
            break
        extracted_data.append({
            "date": item["date"],
            "value": item["value"]
        })
    return str(extracted_data)

@mcp.tool()
def get_us_consumer_price_index(limit:int=10) -> str:
    """
    This API returns the monthly and semiannual consumer price index (CPI) of the United States. CPI is widely regarded as the barometer of inflation levels in the broader economy.
    """
    url = 'https://www.alphavantage.co/query?function=CPI&interval=monthly&apikey=' + ALPHA_KEY
    response = requests.get(url)
    news = response.json()

    extracted_data = []
    for i, item in enumerate(news["data"]):
        if i >= limit:
            break
        extracted_data.append({
            "date": item["date"],
            "value": item["value"]
        })
    return str(extracted_data)


@mcp.tool()
def get_us_inflation_index(limit:int=10) -> str:
    """
    This API returns the annual inflation rates (consumer prices) of the United States.
    """
    url = 'https://www.alphavantage.co/query?function=INFLATION&apikey=' + ALPHA_KEY
    response = requests.get(url)
    news = response.json()

    extracted_data = []
    for i, item in enumerate(news["data"]):
        if i >= limit:
            break
        extracted_data.append({
            "date": item["date"],
            "value": item["value"]
        })
    return str(extracted_data)

@mcp.tool()
def get_us_retail_sales_index(limit:int=10) -> str:
    """
    This API returns the monthly Advance Retail Sales: Retail Trade data of the United States.
    """
    url = 'https://www.alphavantage.co/query?function=RETAIL_SALES&apikey=' + ALPHA_KEY
    response = requests.get(url)
    news = response.json()

    extracted_data = []
    for i, item in enumerate(news["data"]):
        if i >= limit:
            break
        extracted_data.append({
            "date": item["date"],
            "value": item["value"]
        })
    return str(extracted_data)

@mcp.tool()
def get_us_durable_goods_index(limit:int=10) -> str:
    """
    This API returns the monthly manufacturers' new orders of durable goods in the United States.
    """
    url = 'https://www.alphavantage.co/query?function=DURABLES&apikey=' + ALPHA_KEY
    response = requests.get(url)
    news = response.json()

    extracted_data = []
    for i, item in enumerate(news["data"]):
        if i >= limit:
            break
        extracted_data.append({
            "date": item["date"],
            "value": item["value"]
        })
    return str(extracted_data)

@mcp.tool()
def get_us_unemployment_data_index(limit:int=10) -> str:
    """
    This API returns the monthly unemployment data of the United States.
    """
    url = 'https://www.alphavantage.co/query?function=UNEMPLOYMENT&apikey=' + ALPHA_KEY
    response = requests.get(url)
    news = response.json()

    extracted_data = []
    for i, item in enumerate(news["data"]):
        if i >= limit:
            break
        extracted_data.append({
            "date": item["date"],
            "value": item["value"]
        })
    return str(extracted_data)

if __name__ == "__main__":
    # Initialize and run the server
    mcp.run(transport='stdio')
