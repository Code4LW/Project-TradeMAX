<#import "blocks/template.ftlh" as t>
<@t.template user>
<hr>
<h1 style="text-align: center; color: deepskyblue">Find what you are looking for right here!</h1>
    <hr>
    <form method="get" action="/">
        <div class="form-row">
            <div class="form-group col-md-10">
                <label for="name" style="font-weight: bold">Search for a specific ad</label>
                <input type="text" name="name" <#if name??>value="${name}"</#if> class="form-control"
                       id="name">
            </div>
            <button style="width: 100%; color: white" type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>
    <hr>
    <div class="card-columns">
        <#list items as item>
            <a href="/item/${item.id}" class="product-link">
                <div class="card text-white bg-primary">
                    <img src="/images/${item.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${item.name}</h5>
                        <p class="card-text">${item.price} tg.</p>
                    </div>
                    <div class="card-footer">
                        ${item.city}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center; color: crimson">No item found :(</h1>
        </#list>
    </div>
</@t.template>