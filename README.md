# elasticsearch-arabic-dialect-plugin
Elasticsearch filter plugin for Arabic dialect and Modern Standard Arabic

Blog (Korean): https://iam.namjun.kim/arabic/nlp/elasticsearch/2022/04/30/elasticsearch-do-arabic-nlp/

## Usage

### Set analyzer

```
PUT http://localhost:9200/arabic/
{
  "settings": {
  	"analysis": {
    	"analyzer": {
        	"arabic_analyzer": {
            	"type": "custom",
              	"tokenizer": "whitespace",
              	"filter": ["arabic_dialect_filter"]
            }
        }
    }
  }
}
```

### Test
Testing text from Al Jazeera privacy notice message.

```
POST http://localhost:9200/arabic/_analyzer

{
	"analyzer": "arabic_analyzer",
	  "text": "يمكنك الاعتماد على الجزيرة في مسألتي الحقيقة والشفافيةنحن نتفهّم أن خصوصيتك على الإنترنت أمر بالغ الأهمية، وموافقتك على تمكيننا من جمع بعض المعلومات الشخصية عنك يتطلب ثقة كبيرة منك."
}
```
