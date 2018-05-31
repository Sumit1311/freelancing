/* A Bison parser, made by GNU Bison 3.0.2.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2013 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

#ifndef YY_YY_OURPYTHON_TAB_H_INCLUDED
# define YY_YY_OURPYTHON_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 1
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    VARIABLE_NAME = 258,
    INTEGER = 259,
    FLOAT = 260,
    TRUE = 261,
    FALSE = 262,
    STRING = 263,
    NEWLINE = 264,
    SEMICOLON = 265,
    BEGIN_INDENT = 266,
    END_INDENT = 267,
    WHILE = 268,
    ELIF = 269,
    KEYWORD_NAME = 270,
    ARITH_H_OP = 271,
    ARITH_L_OP = 272,
    COMP_OP = 273,
    EQUAL_TO = 274,
    IF = 275,
    ELSE = 276,
    AND = 277,
    OR = 278,
    NOT = 279,
    POWER_OP = 280,
    SHIFT_OP = 281,
    B_AND = 282,
    B_OR = 283,
    B_NOT = 284,
    B_XOR = 285,
    FACTOR_OP = 286,
    OPEN_PARAN = 287,
    CLOSE_PARAN = 288,
    COLON = 289,
    PRINT = 290
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE YYSTYPE;
union YYSTYPE
{
#line 41 "ourPython.y" /* yacc.c:1909  */

	Statement* statePtr_;
	Expression* exprPtr_;
    std::string *var_name;
    int int_val;
    float float_val;
    bool bool_val;

#line 99 "ourPython.tab.h" /* yacc.c:1909  */
};
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_OURPYTHON_TAB_H_INCLUDED  */
