package com.github.turansky.react

private const val DANGEROUSLY_SET_INNER_HTML = """
interface DangerouslySetInnerHTML {
    __html: string;
}
"""

private const val STYLE_MEDIA = """
interface StyleMedia {
    type: string;
    matchMedium(mediaquery: string): boolean;
}
"""

private const val INPUT_TYPE = """
type InputType =
  | 'button'
  | 'checkbox'
  | 'color'
  | 'date'
  | 'datetime-local'
  | 'email'
  | 'file'
  | 'hidden'
  | 'image'
  | 'month'
  | 'number'
  | 'password'
  | 'radio'
  | 'range'
  | 'reset'
  | 'search'
  | 'submit'
  | 'tel'
  | 'text'
  | 'time'
  | 'url'
  | 'week';
"""

internal val ADDITIONAL_TYPES = sequenceOf(
    DANGEROUSLY_SET_INNER_HTML,
    STYLE_MEDIA,
    INPUT_TYPE,
).joinToString("")
