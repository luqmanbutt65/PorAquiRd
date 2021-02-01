/**
 * Copyright (C) 2015 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

var RE = {};

RE.currentSelection = {
    "startContainer": 0,
    "startOffset": 0,
    "endContainer": 0,
    "endOffset": 0};

RE.editor = document.getElementById('editor');

RE.setHtml = function(contents) {
    RE.editor.innerHTML = decodeURIComponent(contents.replace(/\+/g, '%20'));
}

RE.getHtml = function() {
    return RE.editor.innerHTML;
}

RE.getText = function() {
    return RE.editor.innerText;
}

RE.setBaseTextColor = function(color) {
    RE.editor.style.color  = color;
}

RE.setBaseFontSize = function(size) {
    RE.editor.style.fontSize = size;
}

RE.setPadding = function(left, top, right, bottom) {
  RE.editor.style.paddingLeft = left;
  RE.editor.style.paddingTop = top;
  RE.editor.style.paddingRight = right;
  RE.editor.style.paddingBottom = bottom;
}

RE.setBackgroundColor = function(color) {
    document.body.style.backgroundColor = color;
}

RE.setBackgroundImage = function(image) {
    RE.editor.style.backgroundImage = image;
}

RE.setWidth = function(size) {
    RE.editor.style.minWidth = size;
}

RE.setHeight = function(size) {
    document.body.style.minHeight = size;
}

RE.setTextAlign = function(align) {
    RE.editor.style.textAlign = align;
}

RE.setVerticalAlign = function(align) {
    RE.editor.style.verticalAlign = align;
}

RE.setPlaceholder = function(placeholder) {
    RE.editor.setAttribute("placeholder", placeholder);
}

RE.undo = function() {
    document.execCommand('undo', false, null);
}

RE.redo = function() {
    document.execCommand('redo', false, null);
}

RE.setBold = function() {
    document.execCommand('bold', false, null);
}

RE.setItalic = function() {
    document.execCommand('italic', false, null);
}

RE.setSubscript = function() {
    document.execCommand('subscript', false, null);
}

RE.setSuperscript = function() {
    document.execCommand('superscript', false, null);
}

RE.setStrikeThrough = function() {
    document.execCommand('strikeThrough', false, null);
}

RE.setUnderline = function() {
    document.execCommand('underline', false, null);
}

RE.setBullets = function() {
    document.execCommand('InsertUnorderedList', false, null);
}

RE.setNumbers = function() {
    document.execCommand('InsertOrderedList', false, null);
}

RE.setTextColor = function(color) {
    RE.restorerange();
    document.execCommand("styleWithCSS", null, true);
    document.execCommand('foreColor', false, color);
    document.execCommand("styleWithCSS", null, false);
}

RE.setTextBackgroundColor = function(color) {
    RE.restorerange();
    document.execCommand("styleWithCSS", null, true);
    document.execCommand('hiliteColor', false, color);
    document.execCommand("styleWithCSS", null, false);
}

RE.setTextAndBackgroundColor = function(color1, color2) {
    RE.restorerange();
    document.execCommand("styleWithCSS", null, true);
    document.execCommand('foreColor', false, color1);
    document.execCommand('hiliteColor', false, color2);
    document.execCommand("styleWithCSS", null, false);
}

RE.setHeading = function(heading) {
    document.execCommand('formatBlock', false, '<h'+heading+'>');
}

RE.setIndent = function() {
    document.execCommand('indent', false, null);
}

RE.setOutdent = function() {
    document.execCommand('outdent', false, null);
}

RE.setJustifyLeft = function() {
    document.execCommand('justifyLeft', false, null);
}

RE.setJustifyCenter = function() {
    document.execCommand('justifyCenter', false, null);
}

RE.setJustifyRight = function() {
    document.execCommand('justifyRight', false, null);
}

RE.setBlockquote = function() {
    document.execCommand('formatBlock', false, '<blockquote>');
}

RE.insertHTML = function(html) {
    RE.restorerange();
    document.execCommand('insertHTML', false, html);
}

RE.prepareInsert = function() {
    RE.backuprange();
}

RE.backuprange = function(){
    var selection = window.getSelection();
    if (selection.rangeCount > 0) {
      var range = selection.getRangeAt(0);
      RE.currentSelection = {
          "startContainer": range.startContainer,
          "startOffset": range.startOffset,
          "endContainer": range.endContainer,
          "endOffset": range.endOffset};
    }
}

RE.restorerange = function(){
    var selection = window.getSelection();
    selection.removeAllRanges();
    var range = document.createRange();
    range.setStart(RE.currentSelection.startContainer, RE.currentSelection.startOffset);
    range.setEnd(RE.currentSelection.endContainer, RE.currentSelection.endOffset);
    selection.addRange(range);
}

RE.focus = function() {
    var range = document.createRange();
    range.selectNodeContents(RE.editor);
    range.collapse(false);
    var selection = window.getSelection();
    selection.removeAllRanges();
    selection.addRange(range);
    RE.editor.focus();
}

RE.blurFocus = function() {
    RE.editor.blur();
}

RE.removeFormat = function() {
    execCommand('removeFormat', false, null);
}

RE.blockAllItems = function() {
    var enabledItems = [];
    if (document.queryCommandState('bold')) {
        enabledItems.push('BOLD');
    }
    if (document.queryCommandState('italic')) {
        enabledItems.push('ITALIC');
    }
    if (document.queryCommandState('underline')) {
        enabledItems.push('UNDERLINE');
    }
    if (document.queryCommandState('strikeThrough')) {
        enabledItems.push('STRIKETHROUGH');
    }
    if (document.queryCommandState('foreColor')) {
        enabledItems.push('FORECOLOR');
    }
    if (document.queryCommandState('hiliteColor')) {
        enabledItems.push('HILITECOLOR');
    }
    var enabledEditableItems = encodeURI(enabledItems.join(','));

    JSInterface.callback("~!~!~!" + enabledEditableItems + "~!~!~!" + encodeURI(RE.getHtml()));
}

RE.allowAllItems = function() {
    var allowedItems = [];
    allowedItems.push('BOLD');
    allowedItems.push('ITALIC');
    allowedItems.push('UNDERLINE');
    allowedItems.push('STRIKETHROUGH');
    allowedItems.push('FORECOLOR');
    allowedItems.push('HILITECOLOR');

    var allowedEditableItems = encodeURI(allowedItems.join(','));

    var enabledItems = [];
    if (document.queryCommandState('bold')) {
        enabledItems.push('BOLD');
    }
    if (document.queryCommandState('italic')) {
        enabledItems.push('ITALIC');
    }
    if (document.queryCommandState('underline')) {
        enabledItems.push('UNDERLINE');
    }
    if (document.queryCommandState('strikeThrough')) {
        enabledItems.push('STRIKETHROUGH');
    }
    if (document.queryCommandState('foreColor')) {
        enabledItems.push('FORECOLOR');
    }
    if (document.queryCommandState('hiliteColor')) {
        enabledItems.push('HILITECOLOR');
    }
    var enabledEditableItems = encodeURI(enabledItems.join(','));

    JSInterface.callback(allowedEditableItems + "~!~!~!" + enabledEditableItems + "~!~!~!" + encodeURI(RE.getHtml()));
}

// Event Listeners
RE.editor.addEventListener("click", function(e) {
    var selection = window.getSelection();
    if (selection.rangeCount > 0) {
        var range = selection.getRangeAt(0);
        var text = range.startContainer.data;
        var index = range.startOffset;
        if (typeof text === 'undefined' && index == 0) {
            // Click on beginning of html
            RE.allowAllItems();
        }
        else if (index > 0 && (text[index - 1] == ' ' || text.charCodeAt(index - 1) == 160)) {
            // Click after a space
            RE.allowAllItems();
        }
        else {
            RE.blockAllItems();
        }
    }
});

RE.editor.addEventListener("input", function(e) {
    var selection = window.getSelection();
    if (selection.rangeCount > 0) {
        var range = selection.getRangeAt(0);
        var text = range.startContainer.data;
        var index = range.startOffset;
        if (typeof text === 'undefined') {
            // User just entered something unknown
            RE.allowAllItems();
        }
        else if (index > 0 && text.charCodeAt(index - 1) == 160) {
            // User just entered space
            RE.allowAllItems();
        }
        else {
            RE.blockAllItems();
        }
    }
});

document.addEventListener("selectionchange", function() {
    RE.backuprange();
    var selection = window.getSelection();
    if (selection.rangeCount > 0) {
        var range = selection.getRangeAt(0);
        var start = range.startOffset;
        var end = range.endOffset;
        if (start >= 0 && end >= 0 && end - start > 0) {
            RE.allowAllItems();
        }
    }
});