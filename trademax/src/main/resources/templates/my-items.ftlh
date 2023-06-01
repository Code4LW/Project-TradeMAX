<#import "blocks/template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">My ads</h1>
<hr>
    <form action = "/item/post" method = "post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productName">Enter your item</label>
                <input type="text" required class="form-control"
                       id="itemName" name="name">
            </div>
            <div class="form-group">
                <label for="price">Enter the price</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="price" name="price">
                    <div class="input-group-append">
                        <span class="input-group-text">tg.</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="searchCity">Enter the city</label>
                <input type="text" required class="form-control"
                       id="city" name="city">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea required class="form-control" id="description" name="description"></textarea>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input required type="file" name="file1" id="file1">
                    <label class="custom-file-label" for="file1">Upload image 1</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file2" id="file2">
                    <label class="custom-file-label" for="file2">Upload image 2</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file3" id="file3">
                    <label class="custom-file-label" for="file3">Upload image 3</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-primary">Post the ad</button>
        </form>
    </div>
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
                    <form action="/item/delete/${item.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-danger m-3" style="alignment: center">Delete</button>
                    </form>
                </div>
                                </div>
            </a>
            <hr>
        <#else>
            <h1 style="text-align: center; color: crimson">No item found :(</h1>
        </#list>
    </div>
</@t.template>