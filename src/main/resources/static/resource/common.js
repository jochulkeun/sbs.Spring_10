$('select[data-value]').each(function(index,el) {
	const $el = $(el);

	const defaultvalue = $el.attr('data-value').trim();

	if (defaultvalue > 0) {
		$el.val(defaultvalue);
	}

});