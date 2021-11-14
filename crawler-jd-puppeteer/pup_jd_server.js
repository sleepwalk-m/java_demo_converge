const puppeteer = require('puppeteer');

const http = require('http');
const url = require('url');

let data = {
    result: '',
    param: ''
};


// 创建本地服务器来从其接收数据
const server = http.createServer(async (req, res) => {
    var urlParam = url.parse(req.url, true).query;
    data['param'] = urlParam;
    res.writeHead(200, {'Content-Type': 'application/json'});
    data['result'] = await run(urlParam.server_url, urlParam.level);
    res.end(JSON.stringify({
        data: data
    }));
});

server.listen(9876);

async function run(url, level) {
    const browser = await puppeteer.launch({
        headless: false,
        ignoreHTTPSErrors: true,
        slowMo: 100,
        defaultViewport: {width: 1920, height: 1080}
    });

    try {
        const page = await browser.newPage();
        await page.goto(url);

        // 如果是list 代表走列表页 不是则走详情页 直接返回整个html页面
        if ('list' === level) {
            await page.type('#key', '手机', {delay: 100}); // 输入变慢，像一个用户
            await page.click('.button');
            await page.waitFor(1000);
            autoScroll(page);// 翻页
            await page.waitForSelector("ul.gl-warp");
            var content = await page.$eval("ul.gl-warp.clearfix", element => element.outerHTML);
        }


        if ('detail' === level){
            await page.waitFor(1000);
        }


        console.log(await page.content());
        return await page.content();

    } catch (e) {
        await browser.close();
    } finally {
        await browser.close();
    }
};


//延时函数
function sleep(delay) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            try {
                resolve(1)
            } catch (e) {
                reject(0)
            }
        }, delay)
    })
}


/**
 * 滚动
 * @param page
 * @returns {Promise<void>}
 */
async function autoScroll(page) {
    await page.evaluate(async () => {
        await new Promise((resolve, reject) => {
            var totalHeight = 0;
            var distance = 500;
            var timer = setInterval(() => {
                var scrollHeight = document.body.scrollHeight;
                window.scrollBy(0, distance);
                totalHeight += distance;
                if (totalHeight >= scrollHeight) {
                    clearInterval(timer);
                    resolve();
                }
            }, 100);
        });
    });
}
